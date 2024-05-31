package ru.vsu.cs.raspopov.orderWork.model.entity

import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.vsu.cs.raspopov.exposed.TimeAtLongEntity
import ru.vsu.cs.raspopov.order.model.entity.Order
import ru.vsu.cs.raspopov.orderWork.model.table.OrderWorks

class OrderWork(
    id: EntityID<Long>,
) : TimeAtLongEntity(id, OrderWorks) {

    companion object : LongEntityClass<OrderWork>(OrderWorks)

    var workId by OrderWorks.workId
    var price by OrderWorks.price

    var orderId by OrderWorks.orderId
    val order by Order referencedOn OrderWorks.orderId
}