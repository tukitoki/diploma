package ru.vsu.cs.raspopov.orderEmployee.model.table

import ru.vsu.cs.raspopov.exposed.TimeAtLongIdTable
import ru.vsu.cs.raspopov.order.model.table.Orders

object OrderEmployees : TimeAtLongIdTable() {

    val employeeId = long("employee_id")
    val orderId = reference("order_id", Orders)
}