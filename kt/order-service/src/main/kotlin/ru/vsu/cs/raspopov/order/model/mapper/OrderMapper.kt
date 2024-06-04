package ru.vsu.cs.raspopov.order.model.mapper

import ru.vsu.cs.raspopov.order.model.dto.response.OrderListResponse
import ru.vsu.cs.raspopov.order.model.dto.response.OrderResponse
import ru.vsu.cs.raspopov.order.model.dto.response.OrderTemporaryResponse
import ru.vsu.cs.raspopov.order.model.entity.Order
import ru.vsu.cs.raspopov.orderDetail.model.mapper.toResponse
import ru.vsu.cs.raspopov.orderEmployee.model.mapper.toResponse
import ru.vsu.cs.raspopov.orderWork.model.mapper.toResponse

fun Order.toResponse() = OrderResponse(
    id = this.id.value,
    cost = this.orderWorks.sumOf { it.price }.toBigDecimal(),
    carServiceId = this.carServiceId,
    reservedWindowId = this.reservedWindowId,
    carId = this.carId,

    orderDetails = this.orderDetails.map { it.toResponse() },
    orderWorks = this.orderWorks.map { it.toResponse() },
    orderEmployee = this.orderEmployee.toResponse(),
)

fun Order.toTemporaryResponse() = OrderTemporaryResponse(
    true
)

fun Order.toListResponse() = OrderListResponse(
    id = this.id.value,
    cost = this.orderWorks.sumOf { it.price }.toBigDecimal(),
    carServiceId = this.carServiceId,
    reservedWindowId = this.reservedWindowId,
    carId = this.carId,
)