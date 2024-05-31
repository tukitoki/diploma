package ru.vsu.cs.raspopov.order.model.entity

import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.vsu.cs.raspopov.exposed.TimeAtLongEntity
import ru.vsu.cs.raspopov.order.model.table.Orders
import ru.vsu.cs.raspopov.orderDetail.model.entity.OrderDetail
import ru.vsu.cs.raspopov.orderDetail.model.table.OrderDetails
import ru.vsu.cs.raspopov.orderEmployee.model.entity.OrderEmployee
import ru.vsu.cs.raspopov.orderEmployee.model.table.OrderEmployees
import ru.vsu.cs.raspopov.orderWork.model.entity.OrderWork
import ru.vsu.cs.raspopov.orderWork.model.table.OrderWorks

class Order(
    id: EntityID<Long>,
) : TimeAtLongEntity(id, Orders) {

    companion object : LongEntityClass<Order>(Orders)

    var customerId by Orders.customerId
    var carServiceId by Orders.carServiceId
    var carId by Orders.carId
    var reservedWindowId by Orders.reservedWindowId

    var status by Orders.status
    var description by Orders.description

    val orderDetails by OrderDetail referrersOn OrderDetails.orderId
    val orderWorks by OrderWork referrersOn OrderWorks.orderId
    val orderEmployee by OrderEmployee referencedOn OrderEmployees.orderId
}