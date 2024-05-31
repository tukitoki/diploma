package ru.vsu.cs.raspopov.orderDetail.model.mapper

import ru.vsu.cs.raspopov.orderDetail.model.dto.response.OrderDetailResponse
import ru.vsu.cs.raspopov.orderDetail.model.entity.OrderDetail

fun OrderDetail.toResponse() = OrderDetailResponse(
    id = this.id.value,
    orderId = this.orderId.value,
    detailId = this.detailId,
    price = this.price.toBigDecimal(),
)