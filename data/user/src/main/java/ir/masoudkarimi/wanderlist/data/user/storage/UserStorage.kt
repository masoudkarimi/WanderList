package ir.masoudkarimi.wanderlist.data.user.storage

import ir.masoudkarimi.wanderlist.data.model.user.User
import kotlinx.coroutines.flow.Flow

internal interface UserStorage {

  fun getLoggedInUser(): Flow<User?>
  suspend fun saveLoggedInUser(user: User?)
}