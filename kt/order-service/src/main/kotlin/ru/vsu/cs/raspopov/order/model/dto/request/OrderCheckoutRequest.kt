package ru.vsu.cs.raspopov.order.model.dto.request

data class OrderCheckoutRequest(
    val orderId: Long,
    val windowId: Long,
)