package ru.vsu.cs.raspopov.client.autoService.dto

data class AutoServiceResponse(
    val orderId: Long,
    val reservedWindowId: Long,
    val performOperation: PerformOperation,
    val operationResult: OperationResult,
)
