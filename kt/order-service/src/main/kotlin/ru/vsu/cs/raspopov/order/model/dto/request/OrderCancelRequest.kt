package ru.vsu.cs.raspopov.order.model.dto.request

data class OrderCancelRequest(
    val id: Long,
    val cancelReason: String,
)