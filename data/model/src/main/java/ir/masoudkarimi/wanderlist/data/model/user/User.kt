package ir.masoudkarimi.wanderlist.data.model.user

import ir.masoudkarimi.wanderlist.data.model.values.Id
import ir.masoudkarimi.wanderlist.data.model.values.Name
import ir.masoudkarimi.wanderlist.data.model.values.Password
import ir.masoudkarimi.wanderlist.data.model.values.Username

data class User(
  val id: Id,
  val name: Name?,
  val username: Username,
  val password: Password,
)

val sampleUser = User(
  id = Id(1),
  name = Name("Masoud"),
  username = Username("masoud1234"),
  password = Password("abcd1234"),
)