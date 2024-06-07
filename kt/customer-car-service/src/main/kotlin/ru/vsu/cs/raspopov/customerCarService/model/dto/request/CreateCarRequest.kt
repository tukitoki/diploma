package ru.vsu.cs.raspopov.customerCarService.model.dto.request

data class CreateCarRequest(
    val carId: Long,
    val mileage: Int,
    val isPrimary: Boolean,
)