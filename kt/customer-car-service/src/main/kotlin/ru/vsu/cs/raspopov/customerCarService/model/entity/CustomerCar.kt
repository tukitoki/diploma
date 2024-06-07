package ru.vsu.cs.raspopov.customerCarService.model.entity

import org.jetbrains.exposed.dao.id.EntityID
import ru.vsu.cs.raspopov.customerCarService.model.table.CustomerCars
import ru.vsu.cs.raspopov.exposed.TimeAtLongEntity

class CustomerCar(
    id: EntityID<Long>,
) : TimeAtLongEntity(id, CustomerCars) {

    var customerId by CustomerCars.customerId
    var carId by CustomerCars.carId
    var mileage by CustomerCars.mileage
    var isPrimary by CustomerCars.isPrimary
}