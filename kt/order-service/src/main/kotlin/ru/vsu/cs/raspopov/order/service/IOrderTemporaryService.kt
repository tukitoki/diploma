package ru.vsu.cs.raspopov.order.service

import ru.vsu.cs.raspopov.customer.dto.CustomerDto
import ru.vsu.cs.raspopov.order.model.dto.request.OrderCreateRequest
import ru.vsu.cs.raspopov.order.model.dto.request.OrderTempUpdateRequest
import ru.vsu.cs.raspopov.order.model.dto.response.OrderTemporaryResponse

interface IOrderTemporaryService {

    fun getTemporaryOrderState(customer: CustomerDto): OrderTemporaryResponse

    fun createTemporaryOrderState(request: OrderCreateRequest, customer: CustomerDto): OrderTemporaryResponse

    fun updateTemporaryOrderState(request: OrderTempUpdateRequest, customer: CustomerDto): OrderTemporaryResponse
}