package ru.vsu.cs.raspopov.client.autoService.dto

data class OrderUpdateMessage(
    val orderId: Long,
    val windowId: Long,
    val serviceId: Long?,
)