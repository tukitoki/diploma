package ru.vsu.cs.raspopov.userservice.common.exposed

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.id.EntityID

abstract class TimeAtLongEntity(
    id: EntityID<Long>,
    table: TimeAtLongIdTable,
) : LongEntity(id) {

    var createdAt by table.createdAt
    var updatedAt by table.updatedAt
}