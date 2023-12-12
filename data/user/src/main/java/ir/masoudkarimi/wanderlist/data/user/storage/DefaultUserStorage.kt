package ir.masoudkarimi.wanderlist.data.user.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import ir.masoudkarimi.wanderlist.data.model.user.User
import ir.masoudkarimi.wanderlist.data.model.values.Id
import ir.masoudkarimi.wanderlist.data.model.values.Name
import ir.masoudkarimi.wanderlist.data.model.values.Password
import ir.masoudkarimi.wanderlist.data.model.values.Username
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal val USER_ID = longPreferencesKey("user_id")
internal val USER_NAME = stringPreferencesKey("user_name")
internal val USER_USERNAME = stringPreferencesKey("user_username")
internal val USER_PASSWORD = stringPreferencesKey("user_password")

internal class DefaultUserStorage @Inject constructor(
  private val dataStore: DataStore<Preferences>,
) : UserStorage {

  override fun getLoggedInUser(): Flow<User?> = dataStore.data.map { preferences ->
    val id = preferences[USER_ID] ?: return@map null
    val name = preferences[USER_NAME]?.takeIf(String::isNotEmpty)
    val username = preferences[USER_USERNAME] ?: return@map null
    val password = preferences[USER_PASSWORD] ?: return@map null

    return@map User(
      id = Id(id),
      name = name?.let(::Name),
      username = Username(username),
      password = Password(password),
    )
  }.distinctUntilChanged()

  override suspend fun saveLoggedInUser(user: User?) {
    dataStore.edit { preferences ->
      if (user == null) {
        preferences.remove(USER_ID)
        preferences.remove(USER_NAME)
        preferences.remove(USER_USERNAME)
        preferences.remove(USER_PASSWORD)
        return@edit
      }

      preferences[USER_ID] = user.id.value
      preferences[USER_NAME] = user.name?.value.orEmpty()
      preferences[USER_USERNAME] = user.username.value
      preferences[USER_PASSWORD] = user.password.value
    }
  }
}