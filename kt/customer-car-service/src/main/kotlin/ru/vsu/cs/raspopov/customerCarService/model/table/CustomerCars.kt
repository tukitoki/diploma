package ru.vsu.cs.raspopov.customerCarService.model.table

import ru.vsu.cs.raspopov.exposed.TimeAtLongIdTable

object CustomerCars : TimeAtLongIdTable("customer_cars") {

    val customerId = long("customer_id")
    val carId = long("car_id")
    val mileage = integer("mileage")
    val isPrimary = bool("is_primary")
}