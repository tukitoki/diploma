package ru.vsu.cs.raspopov.orderWork.model.table

import ru.vsu.cs.raspopov.exposed.TimeAtLongIdTable
import ru.vsu.cs.raspopov.order.model.table.Orders

object OrderWorks : TimeAtLongIdTable() {

    val workId = long("work_id")
    val price = double("price")
    val orderId = reference("order_id", Orders)
}