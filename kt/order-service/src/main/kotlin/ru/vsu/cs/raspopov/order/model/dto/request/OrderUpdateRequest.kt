package ru.vsu.cs.raspopov.order.model.dto.request

data class OrderUpdateRequest(
    val orderId: Long,
    val windowsId: Long,
    val serviceId: Long,
)