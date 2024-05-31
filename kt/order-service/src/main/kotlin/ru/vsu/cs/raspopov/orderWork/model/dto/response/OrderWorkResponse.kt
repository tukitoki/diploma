package ru.vsu.cs.raspopov.orderWork.model.dto.response

import java.math.BigDecimal

data class OrderWorkResponse(
    val id: Long,
    val orderId: Long,
    val workId: Long,
    val price: BigDecimal,
)
