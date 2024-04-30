package ru.vsu.cs.raspopov.coreservice.users.model.table

import ru.vsu.cs.raspopov.exposed.TimeAtLongIdTable
import ru.vsu.cs.raspopov.coreservice.users.model.enums.Role
import ru.vsu.cs.raspopov.coreservice.users.model.enums.UserStatus

object Users : TimeAtLongIdTable() {

    val username = varchar("username", 50)
    val password = varchar("password", 100)
    val email = varchar("email", 50).nullable()
    val phone = varchar("phone", 14)
    val status = enumerationByName("status", 20, UserStatus::class).default(UserStatus.UNBLOCKED)
    val role = enumerationByName("role", 20, Role::class)
}