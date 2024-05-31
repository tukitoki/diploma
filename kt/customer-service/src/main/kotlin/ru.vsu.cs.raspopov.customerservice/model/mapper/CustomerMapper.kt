package ru.vsu.cs.raspopov.customerservice.model.mapper

import ru.vsu.cs.raspopov.customerservice.model.dto.response.CustomerResponse
import ru.vsu.cs.raspopov.customerservice.model.entity.Customer

fun Customer.toResponse() = CustomerResponse(this.userId)