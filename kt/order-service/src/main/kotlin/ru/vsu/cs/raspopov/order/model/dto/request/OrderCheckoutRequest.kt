package ru.vsu.cs.raspopov.order.model.dto.request

data class OrderCheckoutRequest(
    val orderId: String,
    val windowId: Long,
)