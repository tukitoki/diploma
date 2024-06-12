package ru.vsu.cs.raspopov.orderAudit.model.dto.request

import ru.vsu.cs.raspopov.order.model.enums.OrderStatus

data class AuditEvent(
    val orderId: Long,
    val userId: Long,
    val newStatus: OrderStatus,
    val oldStatus: OrderStatus,
    val description: String,
)
