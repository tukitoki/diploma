package ru.vsu.cs.raspopov.customerservice.model.entity

import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import ru.vsu.cs.raspopov.customerservice.model.table.Customers
import ru.vsu.cs.raspopov.exposed.TimeAtLongEntity

class Customer(
    id: EntityID<Long>,
) : TimeAtLongEntity(id, Customers) {

    companion object : LongEntityClass<Customer>(Customers)

    var userId by Customers.userId
}