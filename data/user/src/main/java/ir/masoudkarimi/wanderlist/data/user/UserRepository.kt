package ir.masoudkarimi.wanderlist.data.user

import ir.masoudkarimi.wanderlist.data.model.user.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

  fun getLoggedInUser(): Flow<User?>
  suspend fun clearLoggedInUser()
  suspend fun registerUser(name: String?, username: String, password: String)
}