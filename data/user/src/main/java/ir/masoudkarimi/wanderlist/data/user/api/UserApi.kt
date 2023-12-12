package ir.masoudkarimi.wanderlist.data.user.api

internal interface UserApi {

  suspend fun createUser(name: String?, username: String, password: String): UserDto
}