package ru.vsu.cs.raspopov.order.service

import ru.vsu.cs.raspopov.order.model.dto.request.OrderCancelRequest
import ru.vsu.cs.raspopov.order.model.dto.request.OrderCheckoutRequest
import ru.vsu.cs.raspopov.order.model.dto.request.OrderCreateRequest
import ru.vsu.cs.raspopov.order.model.dto.request.OrderTempUpdateRequest
import ru.vsu.cs.raspopov.order.model.dto.response.OrderResponse
import ru.vsu.cs.raspopov.order.model.dto.response.OrderTemporaryResponse
import ru.vsu.cs.raspopov.customer.dto.CustomerDto
import ru.vsu.cs.raspopov.order.model.dto.request.OrderUpdateRequest
import ru.vsu.cs.raspopov.order.model.dto.response.OrderListResponse

interface IOrderService {

    fun getAllOrders(customer: CustomerDto): Collection<OrderListResponse>

    fun getOrderById(customer: CustomerDto, id: Long): OrderResponse

    fun checkout(request: OrderCheckoutRequest, customer: CustomerDto): OrderResponse

    fun confirmOrder(customer: CustomerDto, id: Long)

    fun cancelOrder(customer: CustomerDto, request: OrderCancelRequest)

    fun updateOrder(customer: CustomerDto, request: OrderUpdateRequest): OrderResponse
}