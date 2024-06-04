package ru.vsu.cs.raspopov.client.autoService.dto

data class AutoServiceResponse(
    val orderId: Long,
    val performOperation: PerformOperation,
    val operationResult: OperationResult,
)
