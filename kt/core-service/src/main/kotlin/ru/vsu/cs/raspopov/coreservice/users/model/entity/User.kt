package ru.vsu.cs.raspopov.coreservice.users.model.entity

import ru.vsu.cs.raspopov.exposed.TimeAtLongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.vsu.cs.raspopov.coreservice.users.model.dto.UserDto
import ru.vsu.cs.raspopov.coreservice.users.model.table.Users

class User(id: EntityID<Long>) : TimeAtLongEntity(id, Users) {

    var username by Users.username
    var password by Users.password
    var email by Users.email
    var phone by Users.phone
    var status by Users.status
    var role by Users.role

    fun toDto() = UserDto(this)

    companion object : LongEntityClass<User>(Users)
}