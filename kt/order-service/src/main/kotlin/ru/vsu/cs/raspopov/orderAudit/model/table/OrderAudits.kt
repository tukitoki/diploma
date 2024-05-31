package ru.vsu.cs.raspopov.orderAudit.model.table

import ru.vsu.cs.raspopov.exposed.TimeAtLongIdTable
import ru.vsu.cs.raspopov.order.model.enums.OrderStatus
import ru.vsu.cs.raspopov.order.model.table.Orders
import ru.vsu.cs.raspopov.orderAudit.model.enums.OrderAction

object OrderAudits : TimeAtLongIdTable() {

    val moveFromStatus = enumerationByName("status", 100, OrderStatus::class)

    val userAction = enumerationByName("user_action", 100, OrderAction::class)
    val userId = long("user_id")

    val cancelReason = varchar("cancel_reason", 500).nullable()

    val orderId = reference("order_id", Orders)
}