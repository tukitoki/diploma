package ru.vsu.cs.raspopov.customerCarService.model.dto.response

data class CustomerCarResponse(
    val carId: Long,
    val mileage: Int,
    val isPrimary: Boolean,
)