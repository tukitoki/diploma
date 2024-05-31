package ru.vsu.cs.raspopov.orderDetail.model.dto.request

import java.math.BigDecimal

data class OrderDetailCreateRequest(
    val detailId: Long,
    val price: BigDecimal,
)