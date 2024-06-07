package ru.vsu.cs.raspopov.client.autoService.dto

data class AutoServiceCancelResponse(
    val orderId: Long,
    val reservedWindowId: Long,
    val cancelReason: String,
)