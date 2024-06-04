package ru.vsu.cs.raspopov.order.model.dto.request

data class OrderUpdateRequest(
    val orderId: Long,
    val windowId: Long,
    val serviceId: Long? = null,
)