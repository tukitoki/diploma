package ru.vsu.cs.raspopov.orderEmployee.model.entity

import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.vsu.cs.raspopov.exposed.TimeAtLongEntity
import ru.vsu.cs.raspopov.order.model.entity.Order
import ru.vsu.cs.raspopov.orderEmployee.model.table.OrderEmployees

class OrderEmployee(
    id: EntityID<Long>,
) : TimeAtLongEntity(id, OrderEmployees) {

    companion object : LongEntityClass<OrderEmployee>(OrderEmployees)

    var employeeId by OrderEmployees.employeeId

    var orderId by OrderEmployees.orderId
    val order by Order referencedOn OrderEmployees.orderId
}