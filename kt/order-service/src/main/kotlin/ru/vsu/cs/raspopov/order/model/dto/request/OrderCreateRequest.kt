package ru.vsu.cs.raspopov.order.model.dto.request

import ru.vsu.cs.raspopov.orderDetail.model.dto.request.OrderDetailCreateRequest
import ru.vsu.cs.raspopov.orderEmployee.model.dto.request.OrderEmployeeCreateRequest
import ru.vsu.cs.raspopov.orderWork.model.dto.request.OrderWorkCreateRequest

data class OrderCreateRequest(
    val customerId: Long? = null,
    val carServiceId: Long? = null,
    val carId: Long? = null,
    val reservedWindowId: Long? = null,

    val orderDetails: List<OrderDetailCreateRequest> = emptyList(),
    val orderWorks: List<OrderWorkCreateRequest> = emptyList(),
    val orderEmployee: OrderEmployeeCreateRequest? = null,
)
