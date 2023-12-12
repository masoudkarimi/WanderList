package ir.masoudkarimi.wanderlist.data.user.api

import ir.masoudkarimi.wanderlist.data.model.user.User
import ir.masoudkarimi.wanderlist.data.model.values.Id
import ir.masoudkarimi.wanderlist.data.model.values.Name
import ir.masoudkarimi.wanderlist.data.model.values.Password
import ir.masoudkarimi.wanderlist.data.model.values.Username

internal data class UserDto(val id: Long, val name: String?, val username: String, val password: String)

internal fun UserDto.toDomainModel() = User(
  id = Id(id),
  name = name?.let(::Name),
  username = Username(username),
  password = Password(password),
)