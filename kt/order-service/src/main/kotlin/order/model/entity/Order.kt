package ru.vsu.cs.raspopov.order.model.entity

import org.jetbrains.exposed.dao.id.EntityID
import ru.vsu.cs.raspopov.exposed.TimeAtLongEntity
import ru.vsu.cs.raspopov.order.model.table.Orders

class Order(
    id: EntityID<Long>,
) : TimeAtLongEntity(id, Orders) {

    var customerId by Orders.customerId
    var carServiceId by Orders.carServiceId
    var carId by Orders.carId
    var status by Orders.status

    var cancelledUserId by Orders.cancelledUserId
    var cancelReason by Orders.cancelReason
}