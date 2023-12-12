package ir.masoudkarimi.wanderlist.data.user.storage


import ir.masoudkarimi.wanderlist.data.model.user.User
import ir.masoudkarimi.wanderlist.data.model.values.Id
import ir.masoudkarimi.wanderlist.data.model.values.Name
import ir.masoudkarimi.wanderlist.data.model.values.Password
import ir.masoudkarimi.wanderlist.data.model.values.Username
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class FakeUserStorage @Inject constructor() : UserStorage {
  data class UserSimulator(
    val id: Long?,
    val name: String?,
    val username: String?,
    val password: String?,
  )

  private val user = MutableStateFlow<UserSimulator?>(null)

  override fun getLoggedInUser(): Flow<User?> {
    return user.map { userSimulator ->
      userSimulator?.id ?: return@map null
      userSimulator.username ?: return@map null
      userSimulator.password ?: return@map null

      User(
        id = Id(userSimulator.id),
        name = userSimulator.name?.let(::Name),
        username = Username(userSimulator.username),
        password = Password(userSimulator.password),
      )
    }.distinctUntilChanged()
  }

  override suspend fun saveLoggedInUser(user: User?) {
    if (user == null) {
      this.user.emit(this.user.value?.copy(id = null))
      this.user.emit(this.user.value?.copy(name = null))
      this.user.emit(this.user.value?.copy(username = null))
      this.user.emit(this.user.value?.copy(password = null))

      return
    }

    this.user.emit(
      UserSimulator(
        id = user.id.value,
        name = user.name?.value,
        username = user.username.value,
        password = user.password.value,
      ),
    )
  }
}
