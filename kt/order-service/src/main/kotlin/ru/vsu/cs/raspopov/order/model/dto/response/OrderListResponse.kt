package ru.vsu.cs.raspopov.order.model.dto.response

import java.math.BigDecimal

data class OrderListResponse(
    val id: Long,
    val cost: BigDecimal,
    val carServiceId: Long,
    val reservedWindowId: Long,
    val carId: Long,
)