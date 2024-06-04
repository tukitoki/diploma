package ru.vsu.cs.raspopov.order.api.docs

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import ru.vsu.cs.raspopov.customer.dto.CustomerDto
import ru.vsu.cs.raspopov.order.model.dto.request.OrderCancelRequest
import ru.vsu.cs.raspopov.order.model.dto.request.OrderCheckoutRequest
import ru.vsu.cs.raspopov.order.model.dto.request.OrderUpdateRequest
import ru.vsu.cs.raspopov.order.model.dto.response.OrderListResponse
import ru.vsu.cs.raspopov.order.model.dto.response.OrderResponse

@Tag(name = "Order API", description = "Order API для работы с временными заказами")
interface OrderAPI {

    @Operation(summary = "Получение всех заказов пользователя")
    fun getAllOrders(
        @Parameter(hidden = true) customer: CustomerDto,
    ): ResponseEntity<Collection<OrderListResponse>>

    @Operation(summary = "Получение заказа по id")
    fun getOrderById(
        @Parameter(hidden = true) customer: CustomerDto,
        id: Long,
    ): ResponseEntity<OrderResponse>

    @Operation(summary = "Оформление заказа")
    fun checkout(
        request: OrderCheckoutRequest,
        @Parameter(hidden = true) customer: CustomerDto,
    ): ResponseEntity<OrderResponse>

    @Operation(summary = "Подтвердить заказ")
    fun confirmOrder(
        @Parameter(hidden = true) customer: CustomerDto,
        id: Long,
    ): ResponseEntity<Unit>

    @Operation(summary = "Отменить заказ")
    fun cancelOrder(
        @Parameter(hidden = true) customer: CustomerDto,
        request: OrderCancelRequest,
    ): ResponseEntity<Unit>

    @Operation(summary = "Изменить заказ")
    fun updateOrder(
        @Parameter(hidden = true) customer: CustomerDto,
        request: OrderUpdateRequest,
    )
}