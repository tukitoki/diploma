package ru.vsu.cs.raspopov.userservice.model.table

import ru.vsu.cs.raspopov.userservice.common.exposed.TimeAtLongIdTable

object Users : TimeAtLongIdTable() {

    val username = varchar("username", 50)
    val password = varchar("password", 100)
    val email = varchar("email", 50)
    val phone = varchar("phone", 14).nullable()
    val isBlocked = bool("isBlocked").default(false)

}