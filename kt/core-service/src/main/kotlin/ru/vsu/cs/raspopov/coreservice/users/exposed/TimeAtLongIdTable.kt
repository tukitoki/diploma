package ru.vsu.cs.raspopov.coreservice.users.exposed

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.datetime

abstract class TimeAtLongIdTable : LongIdTable() {

    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at")
}