package ir.masoudkarimi.wanderlist.data.user.api

import javax.inject.Inject
import kotlin.random.Random

internal class FakeUserApi @Inject constructor() : UserApi {

  override suspend fun createUser(name: String?, username: String, password: String): UserDto {
    return UserDto(
      id = Random.nextLong(),
      name = name,
      username = username,
      password = password,
    )
  }
}