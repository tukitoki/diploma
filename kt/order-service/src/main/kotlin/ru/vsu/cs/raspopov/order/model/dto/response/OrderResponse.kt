package ru.vsu.cs.raspopov.order.model.dto.response

import ru.vsu.cs.raspopov.order.model.enums.OrderStatus
import ru.vsu.cs.raspopov.orderDetail.model.dto.response.OrderDetailResponse
import ru.vsu.cs.raspopov.orderEmployee.model.dto.response.OrderEmployeeResponse
import ru.vsu.cs.raspopov.orderWork.model.dto.response.OrderWorkResponse
import java.math.BigDecimal

data class OrderResponse(
    val id: Long,
    val cost: BigDecimal,
    val customerId: Long? = null,
    val carServiceId: Long? = null,
    val carId: Long? = null,
    val reservedWindowId: Long? = null,
    val status: OrderStatus,

    val orderDetails: List<OrderDetailResponse>,
    val orderWorks: List<OrderWorkResponse>,
    val orderEmployee: OrderEmployeeResponse?,
)