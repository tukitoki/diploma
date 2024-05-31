package ru.vsu.cs.raspopov.orderEmployee.model.mapper

import ru.vsu.cs.raspopov.orderEmployee.model.dto.response.OrderEmployeeResponse
import ru.vsu.cs.raspopov.orderEmployee.model.entity.OrderEmployee

fun OrderEmployee.toResponse() = OrderEmployeeResponse(
    id = this.id.value,
    orderId = this.orderId.value,
    employeeId = this.employeeId,
)