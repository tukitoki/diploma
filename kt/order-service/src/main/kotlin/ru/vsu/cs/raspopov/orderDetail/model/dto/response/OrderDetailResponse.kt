package ru.vsu.cs.raspopov.orderDetail.model.dto.response

import java.math.BigDecimal

data class OrderDetailResponse(
    val id: Long,
    val orderId: Long,
    val detailId: Long,
    val price: BigDecimal,
)