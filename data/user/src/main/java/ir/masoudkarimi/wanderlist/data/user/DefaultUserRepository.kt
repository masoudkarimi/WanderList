package ir.masoudkarimi.wanderlist.data.user

import ir.masoudkarimi.wanderlist.data.model.user.User
import ir.masoudkarimi.wanderlist.data.user.api.UserApi
import ir.masoudkarimi.wanderlist.data.user.api.toDomainModel
import ir.masoudkarimi.wanderlist.data.user.storage.UserStorage
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class DefaultUserRepository @Inject constructor(
  private val userApi: UserApi,
  private val userStorage: UserStorage,
) : UserRepository {

  override fun getLoggedInUser(): Flow<User?> {
    return userStorage.getLoggedInUser()
  }

  override suspend fun clearLoggedInUser() {
    userStorage.saveLoggedInUser(null)
  }

  override suspend fun registerUser(name: String?, username: String, password: String) {
    val userDto = userApi.createUser(name, username, password)
    userStorage.saveLoggedInUser(userDto.toDomainModel())
  }
}