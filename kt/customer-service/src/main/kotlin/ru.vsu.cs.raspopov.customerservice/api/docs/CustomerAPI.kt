package ru.vsu.cs.raspopov.customerservice.api.docs

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import ru.vsu.cs.raspopov.customerservice.model.dto.request.CustomerCreateRequest
import ru.vsu.cs.raspopov.customerservice.model.dto.response.CustomerResponse

@Tag(name = "Customer API", description = "API для работы с customer")
interface CustomerAPI {

    @Operation(description = "Создание сustomer")
    fun createCustomer(request: CustomerCreateRequest): ResponseEntity<CustomerResponse>

    @Operation(description = "Получение customer по id")
    fun getCustomerById(id: Long): ResponseEntity<CustomerResponse>

}