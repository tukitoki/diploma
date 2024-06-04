package ru.vsu.cs.raspopov.order.api

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.vsu.cs.raspopov.customer.dto.CustomerDto
import ru.vsu.cs.raspopov.order.api.docs.OrderTemporaryAPI
import ru.vsu.cs.raspopov.order.model.dto.request.OrderCreateRequest
import ru.vsu.cs.raspopov.order.model.dto.request.OrderTempUpdateRequest
import ru.vsu.cs.raspopov.order.model.dto.response.OrderTemporaryResponse
import ru.vsu.cs.raspopov.order.service.impl.OrderTemporaryService

@RequestMapping("/api/orders/temporary")
@RestController
class OrderTemporaryController(
    private val orderTemporaryService: OrderTemporaryService,
) : OrderTemporaryAPI {

    @GetMapping
    override fun getTemporaryOrderState(
        @AuthenticationPrincipal customer: CustomerDto,
    ): ResponseEntity<OrderTemporaryResponse> {
        return ok(orderTemporaryService.getTemporaryOrderState(customer))
    }

    @PostMapping
    override fun createTemporaryOrderState(
        @RequestBody request: OrderCreateRequest,
        @AuthenticationPrincipal customer: CustomerDto,
    ): ResponseEntity<OrderTemporaryResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(orderTemporaryService.createTemporaryOrderState(request, customer))
    }

    @PatchMapping
    override fun updateTemporaryOrderState(
        @RequestBody request: OrderTempUpdateRequest,
        @AuthenticationPrincipal customer: CustomerDto,
    ): ResponseEntity<OrderTemporaryResponse> {
        return ok(orderTemporaryService.updateTemporaryOrderState(request, customer))
    }
}