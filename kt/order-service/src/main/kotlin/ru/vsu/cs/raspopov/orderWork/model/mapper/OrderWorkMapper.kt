package ru.vsu.cs.raspopov.orderWork.model.mapper

import ru.vsu.cs.raspopov.orderWork.model.dto.response.OrderWorkResponse
import ru.vsu.cs.raspopov.orderWork.model.entity.OrderWork

fun OrderWork.toResponse() = OrderWorkResponse(
    id = this.id.value,
    orderId = this.orderId.value,
    workId = this.workId,
    price = this.price.toBigDecimal(),
)