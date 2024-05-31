package ru.vsu.cs.raspopov.customerservice.model.table

import ru.vsu.cs.raspopov.exposed.TimeAtLongIdTable

object Customers : TimeAtLongIdTable() {

    val userId = long("user_id").autoIncrement()
}