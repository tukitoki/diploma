package ru.vsu.cs.raspopov.order.model.table

import ru.vsu.cs.raspopov.exposed.TimeAtLongIdTable
import ru.vsu.cs.raspopov.order.model.enums.OrderStatus

object Orders : TimeAtLongIdTable() {

    val customerId = long("customer_id")
    val carServiceId = long("car_service_id")
    val carId = long("car_id")
    val status = enumerationByName("status", 100, OrderStatus::class)

    val cancelledUserId = long("cancelled_user_id").nullable()
    val cancelReason = varchar("cancel_reason", 500).nullable()
}