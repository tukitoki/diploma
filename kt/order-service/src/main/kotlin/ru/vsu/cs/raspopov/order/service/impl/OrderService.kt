package ru.vsu.cs.raspopov.order.service.impl

import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.vsu.cs.raspopov.common.exception.ExceptionCode
import ru.vsu.cs.raspopov.common.exception.GeneralException
import ru.vsu.cs.raspopov.customer.dto.CustomerDto
import ru.vsu.cs.raspopov.exposed.findThrowableById
import ru.vsu.cs.raspopov.exposed.findThrowableByPredicate
import ru.vsu.cs.raspopov.order.model.dto.request.OrderCancelRequest
import ru.vsu.cs.raspopov.order.model.dto.request.OrderCheckoutRequest
import ru.vsu.cs.raspopov.order.model.dto.request.OrderCreateRequest
import ru.vsu.cs.raspopov.order.model.dto.request.OrderTempUpdateRequest
import ru.vsu.cs.raspopov.order.model.dto.response.OrderResponse
import ru.vsu.cs.raspopov.order.model.dto.response.OrderTemporaryResponse
import ru.vsu.cs.raspopov.order.model.entity.Order
import ru.vsu.cs.raspopov.order.model.entity.confirm
import ru.vsu.cs.raspopov.order.model.enums.OrderStatus
import ru.vsu.cs.raspopov.order.model.mapper.toListResponse
import ru.vsu.cs.raspopov.order.model.mapper.toResponse
import ru.vsu.cs.raspopov.order.model.mapper.toTemporaryResponse
import ru.vsu.cs.raspopov.order.model.table.Orders
import ru.vsu.cs.raspopov.order.service.IOrderService
import ru.vsu.cs.raspopov.order.service.impl.useCases.OrderCheckoutUseCase

@Transactional
@Service
class OrderService(
    private val orderTemporaryService: OrderTemporaryService,
    private val checkoutUseCase: OrderCheckoutUseCase,
) : IOrderService {

    override fun getAllOrders(
        customer: CustomerDto,
    ) = Order.find {
        Orders.customerId.eq(customer.id) and Orders.status.neq(OrderStatus.TEMPORARY)
    }.map { it.toListResponse() }

    override fun getOrderById(
        customer: CustomerDto,
        id: Long,
    ) = findThrowableOrderById(id, Orders.customerId.eq(customer.id))
        .toResponse()

    override fun checkout(
        request: OrderCheckoutRequest,
        customer: CustomerDto,
    ): OrderResponse {
        val temporaryOrder = orderTemporaryService.findThrowableTemporaryOrderById(customer.id)

        return checkoutUseCase.invoke(request, temporaryOrder, customer)
    }

    override fun confirmOrder(customer: CustomerDto, id: Long) {
        val order = findThrowableOrderById(id, Orders.customerId.eq(customer.id))
        order.confirm()

        // TODO: implement work with schedule service
    }

    override fun cancelOrder(customer: CustomerDto, request: OrderCancelRequest) {
        val order = findThrowableOrderById(request.id, Orders.customerId.eq(customer.id))

        if (order.status.isCancellableStatus()) {
            order.status = OrderStatus.CANCELED
        } else {
            throw GeneralException("Order can't be cancelled due status", HttpStatus.BAD_REQUEST)
        }

        if (order.status.isScheduled()) {
            // TODO: implement work with schedule service
        }
    }

    internal fun findThrowableOrderById(
        id: Long,
        predicate: Op<Boolean>,
    ) = Orders.findThrowableById(id, GeneralException(ExceptionCode.ORDER_NOT_FOUND_BY_ID), predicate)
        .let { Order.wrapRow(it) }
}