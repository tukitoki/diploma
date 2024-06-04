package ru.vsu.cs.raspopov.order.api.docs

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import ru.vsu.cs.raspopov.customer.dto.CustomerDto
import ru.vsu.cs.raspopov.order.model.dto.request.OrderCreateRequest
import ru.vsu.cs.raspopov.order.model.dto.request.OrderTempUpdateRequest
import ru.vsu.cs.raspopov.order.model.dto.response.OrderTemporaryResponse

@Tag(name = "Order API", description = "Order API для работы с заказами")
interface OrderTemporaryAPI {

    @Operation(summary = "Получение промежуточного состояния заказа")
    fun getTemporaryOrderState(
        @Parameter(hidden = true) customer: CustomerDto,
    ): ResponseEntity<OrderTemporaryResponse>

    @Operation(summary = "Создание промежуточного заказа")
    fun createTemporaryOrderState(
        request: OrderCreateRequest,
        @Parameter(hidden = true) customer: CustomerDto,
    ): ResponseEntity<OrderTemporaryResponse>

    @Operation(summary = "Обновление промежуточного заказа")
    fun updateTemporaryOrderState(
        request: OrderTempUpdateRequest,
        @Parameter(hidden = true) customer: CustomerDto,
    ): ResponseEntity<OrderTemporaryResponse>
}