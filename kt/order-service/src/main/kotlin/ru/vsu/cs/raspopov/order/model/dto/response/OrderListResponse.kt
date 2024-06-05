package ru.vsu.cs.raspopov.order.model.dto.response

import ru.vsu.cs.raspopov.order.model.enums.OrderStatus
import java.math.BigDecimal

data class OrderListResponse(
    val id: Long,
    val cost: BigDecimal,
    val carServiceId: Long,
    val reservedWindowId: Long,
    val carId: Long,
    val status: OrderStatus,
)