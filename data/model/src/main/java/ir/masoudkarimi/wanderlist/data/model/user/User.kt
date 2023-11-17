package ir.masoudkarimi.wanderlist.data.model.user

import ir.masoudkarimi.wanderlist.data.model.values.Id
import ir.masoudkarimi.wanderlist.data.model.values.Name
import ir.masoudkarimi.wanderlist.data.model.values.Password
import ir.masoudkarimi.wanderlist.data.model.values.Username

data class User(
  val id: Id,
  val name: Name,
  val email: Username,
  val password: Password,
)