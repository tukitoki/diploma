package ru.vsu.cs.raspopov.order.service.impl

import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.vsu.cs.raspopov.common.exception.ExceptionCode
import ru.vsu.cs.raspopov.common.exception.GeneralException
import ru.vsu.cs.raspopov.exposed.findThrowableById
import ru.vsu.cs.raspopov.exposed.findThrowableByPredicate
import ru.vsu.cs.raspopov.order.model.dto.request.OrderCancelRequest
import ru.vsu.cs.raspopov.order.model.dto.request.OrderCheckoutRequest
import ru.vsu.cs.raspopov.order.model.dto.request.OrderCreateRequest
import ru.vsu.cs.raspopov.order.model.dto.request.OrderUpdateRequest
import ru.vsu.cs.raspopov.order.model.dto.response.OrderResponse
import ru.vsu.cs.raspopov.order.model.dto.response.OrderTemporaryResponse
import ru.vsu.cs.raspopov.order.model.entity.Order
import ru.vsu.cs.raspopov.order.model.enums.OrderStatus
import ru.vsu.cs.raspopov.order.model.mapper.toResponse
import ru.vsu.cs.raspopov.order.model.mapper.toTemporaryResponse
import ru.vsu.cs.raspopov.order.model.table.Orders
import ru.vsu.cs.raspopov.order.service.IOrderService
import ru.vsu.cs.raspopov.customer.dto.CustomerDto

@Transactional
@Service
class OrderService : IOrderService {

    override fun getAllOrders(
        customer: CustomerDto,
    ) = Order.find {
        Orders.customerId.eq(customer.id) and Orders.status.neq(OrderStatus.TEMPORARY)
    }.map { it.toResponse() }

    override fun getOrderById(
        customer: CustomerDto,
        id: Long,
    ) = findThrowableOrderById(id, Orders.customerId.eq(customer.id))
        .toResponse()

    override fun checkout(
        request: OrderCheckoutRequest,
        customer: CustomerDto,
    ): OrderResponse {
        val temporaryOrder = findThrowableTemporaryOrderById(customer.id)

        TODO("Not yet implemented")
    }

    override fun getTemporaryOrderState(
        customer: CustomerDto,
    ) = findThrowableTemporaryOrderById(customer.id).toTemporaryResponse()

    override fun createTemporaryOrderState(
        request: OrderCreateRequest,
        customer: CustomerDto,
    ): OrderTemporaryResponse {
        TODO()
    }

    override fun updateTemporaryOrderState(
        request: OrderUpdateRequest,
        customer: CustomerDto,
    ): OrderTemporaryResponse {
        val temporaryOrder = findThrowableTemporaryOrderById(customer.id)

        TODO("Not yet implemented")
    }

    override fun confirmOrder(customer: CustomerDto, id: Long) {
        val order = findThrowableOrderById(id, Orders.customerId.eq(customer.id))

        if (order.status.isApproveStatus().not()) {
            throw GeneralException("Order can't be approved due status", HttpStatus.BAD_REQUEST)
        }

        // TODO: implement work with schedule service
    }

    override fun cancelOrder(customer: CustomerDto, id: Long, request: OrderCancelRequest) {
        val order = findThrowableOrderById(id, Orders.customerId.eq(customer.id))

        if (order.status.isCancellableStatus()) {
            order.status = OrderStatus.CANCELED
        } else {
            throw GeneralException("Order can't be cancelled due status", HttpStatus.BAD_REQUEST)
        }

        if (order.status.isScheduled()) {
            // TODO: implement work with schedule service
        }
    }

    private fun findThrowableOrderById(
        id: Long,
        predicate: Op<Boolean>,
    ) = Orders.findThrowableById(id, GeneralException(ExceptionCode.ORDER_NOT_FOUND_BY_ID), predicate)
        .let { Order.wrapRow(it) }

    private fun findThrowableTemporaryOrderById(
        customerId: Long,
    ) = Orders.findThrowableByPredicate(GeneralException(ExceptionCode.TEMPORARY_ORDER_NO_CONTENT)) {
        Orders.customerId.eq(customerId) and Orders.status.eq(OrderStatus.TEMPORARY)
    }.let { Order.wrapRow(it) }
}