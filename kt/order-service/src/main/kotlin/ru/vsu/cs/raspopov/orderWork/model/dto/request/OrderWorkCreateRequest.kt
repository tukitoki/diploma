package ru.vsu.cs.raspopov.orderWork.model.dto.request

import java.math.BigDecimal

data class OrderWorkCreateRequest(
    val workId: Long,
    val price: BigDecimal,
)
