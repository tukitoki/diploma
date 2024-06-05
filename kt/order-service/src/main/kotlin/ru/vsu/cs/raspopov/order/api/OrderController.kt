package ru.vsu.cs.raspopov.order.api

import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.vsu.cs.raspopov.customer.dto.CustomerDto
import ru.vsu.cs.raspopov.order.api.docs.OrderAPI
import ru.vsu.cs.raspopov.order.model.dto.request.OrderCancelRequest
import ru.vsu.cs.raspopov.order.model.dto.request.OrderCheckoutRequest
import ru.vsu.cs.raspopov.order.model.dto.request.OrderUpdateRequest
import ru.vsu.cs.raspopov.order.model.dto.response.OrderListResponse
import ru.vsu.cs.raspopov.order.model.dto.response.OrderResponse
import ru.vsu.cs.raspopov.order.service.impl.OrderService

@RequestMapping("/api/orders")
@RestController
class OrderController(
    private val orderService: OrderService,
) : OrderAPI {

    @GetMapping
    override fun getAllOrders(
        @AuthenticationPrincipal customer: CustomerDto,
    ): ResponseEntity<Collection<OrderListResponse>> {
        return ok(orderService.getAllOrders(customer))
    }

    @GetMapping("/{id}")
    override fun getOrderById(
        @AuthenticationPrincipal customer: CustomerDto,
        @PathVariable id: Long,
    ): ResponseEntity<OrderResponse> {
        return ok(orderService.getOrderById(customer, id))
    }

    @PostMapping("/checkout")
    override fun checkout(
        @RequestBody request: OrderCheckoutRequest,
        @AuthenticationPrincipal customer: CustomerDto,
    ): ResponseEntity<OrderResponse> {
        return ok(orderService.checkout(request, customer))
    }

    @PatchMapping("/{id}/confirm")
    override fun confirmOrder(
        @AuthenticationPrincipal customer: CustomerDto,
        @PathVariable id: Long,
    ): ResponseEntity<Unit> {
        return ok(orderService.confirmOrder(customer, id))
    }

    @PatchMapping("/cancel")
    override fun cancelOrder(
        @AuthenticationPrincipal customer: CustomerDto,
        @RequestBody request: OrderCancelRequest,
    ): ResponseEntity<Unit> {
        return ok(orderService.cancelOrder(customer, request))
    }

    @PatchMapping
    override fun updateOrder(
        @AuthenticationPrincipal customer: CustomerDto,
        @RequestBody request: OrderUpdateRequest,
    ): ResponseEntity<OrderResponse> {
        TODO("Not yet implemented")
    }
}