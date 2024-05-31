package ru.vsu.cs.raspopov.order.model.mapper

import ru.vsu.cs.raspopov.order.model.dto.response.OrderResponse
import ru.vsu.cs.raspopov.order.model.dto.response.OrderTemporaryResponse
import ru.vsu.cs.raspopov.order.model.entity.Order

fun Order.toResponse() = OrderResponse(
    this.id.value
)

fun Order.toTemporaryResponse() = OrderTemporaryResponse(
    true
)