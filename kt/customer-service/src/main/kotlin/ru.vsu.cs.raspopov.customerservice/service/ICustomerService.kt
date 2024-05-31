package ru.vsu.cs.raspopov.customerservice.service

import ru.vsu.cs.raspopov.customerservice.model.dto.request.CustomerCreateRequest
import ru.vsu.cs.raspopov.customerservice.model.dto.response.CustomerResponse

interface ICustomerService {

    fun createCustomer(request: CustomerCreateRequest): CustomerResponse

    fun getCustomerById(id: Long): CustomerResponse
}