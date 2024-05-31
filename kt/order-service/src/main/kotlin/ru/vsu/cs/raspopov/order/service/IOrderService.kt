package ru.vsu.cs.raspopov.order.service

import ru.vsu.cs.raspopov.order.model.dto.request.OrderCancelRequest
import ru.vsu.cs.raspopov.order.model.dto.request.OrderCheckoutRequest
import ru.vsu.cs.raspopov.order.model.dto.request.OrderCreateRequest
import ru.vsu.cs.raspopov.order.model.dto.request.OrderUpdateRequest
import ru.vsu.cs.raspopov.order.model.dto.response.OrderResponse
import ru.vsu.cs.raspopov.order.model.dto.response.OrderTemporaryResponse
import ru.vsu.cs.raspopov.customer.dto.CustomerDto

interface IOrderService {

    fun getAllOrders(customer: CustomerDto): Collection<OrderResponse>

    fun getOrderById(customer: CustomerDto, id: Long): OrderResponse

    fun checkout(request: OrderCheckoutRequest, customer: CustomerDto): OrderResponse

    fun getTemporaryOrderState(customer: CustomerDto): OrderTemporaryResponse

    fun createTemporaryOrderState(request: OrderCreateRequest, customer: CustomerDto): OrderTemporaryResponse

    fun updateTemporaryOrderState(request: OrderUpdateRequest, customer: CustomerDto): OrderTemporaryResponse

    fun confirmOrder(customer: CustomerDto, id: Long)

    fun cancelOrder(customer: CustomerDto, id: Long, request: OrderCancelRequest)

}