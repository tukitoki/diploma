package ru.vsu.cs.raspopov.orderEmployee.model.dto.response

data class OrderEmployeeResponse(
    val id: Long,
    val orderId: Long,
    val employeeId: Long,
)