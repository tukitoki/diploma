package ru.vsu.cs.raspopov.orderDetail.model.entity

import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.vsu.cs.raspopov.exposed.TimeAtLongEntity
import ru.vsu.cs.raspopov.order.model.entity.Order
import ru.vsu.cs.raspopov.orderDetail.model.table.OrderDetails

class OrderDetail(
    id: EntityID<Long>,
) : TimeAtLongEntity(id, OrderDetails) {

    companion object : LongEntityClass<OrderDetail>(OrderDetails)

    var detailId by OrderDetails.detailId
    var price by OrderDetails.price

    var orderId by OrderDetails.orderId
    val order by Order referencedOn  OrderDetails.orderId
}