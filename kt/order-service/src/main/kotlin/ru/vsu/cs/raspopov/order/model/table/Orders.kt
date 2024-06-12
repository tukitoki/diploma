package ru.vsu.cs.raspopov.order.model.table

import ru.vsu.cs.raspopov.exposed.TimeAtLongIdTable
import ru.vsu.cs.raspopov.order.model.enums.OrderStatus

object Orders : TimeAtLongIdTable() {

    val customerId = long("customer_id")
    val carServiceId = long("car_service_id")
    val carId = long("car_id")
    val carMileage = double("car_mileage")
    val reservedWindowId = long("reserved_window_id")

    val status = enumerationByName("status", 100, OrderStatus::class)
    val description = text("description").nullable()
}