package ru.vsu.cs.raspopov.userservice.model.entity

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.id.EntityID
import ru.vsu.cs.raspopov.userservice.common.exposed.TimeAtLongEntity
import ru.vsu.cs.raspopov.userservice.model.table.Users

class User(id: EntityID<Long>) : TimeAtLongEntity(id, Users) {

    var username by Users.username
    var password by Users.password
    var email by Users.email
    var phone by Users.phone
    var isBlocked by Users.isBlocked

}