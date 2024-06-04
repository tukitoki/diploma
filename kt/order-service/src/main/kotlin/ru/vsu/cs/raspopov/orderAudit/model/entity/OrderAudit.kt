package ru.vsu.cs.raspopov.orderAudit.model.entity

import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.vsu.cs.raspopov.exposed.TimeAtLongEntity
import ru.vsu.cs.raspopov.order.model.entity.Order
import ru.vsu.cs.raspopov.orderAudit.model.table.OrderAudits

class OrderAudit(
    id: EntityID<Long>,
) : TimeAtLongEntity(id, OrderAudits) {

    companion object : LongEntityClass<OrderAudit>(OrderAudits)

    var moveFromStatus by OrderAudits.moveFromStatus
    var userAction by OrderAudits.userAction
    var userId by OrderAudits.userId
    var description by OrderAudits.description

    var orderId by OrderAudits.orderId
    val order by Order referencedOn OrderAudits.orderId
}