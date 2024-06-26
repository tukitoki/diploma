package ru.vsu.cs.raspopov.order.service.impl

import org.jetbrains.exposed.sql.and
import org.springframework.stereotype.Service
import ru.vsu.cs.raspopov.common.exception.ExceptionCode
import ru.vsu.cs.raspopov.common.exception.GeneralException
import ru.vsu.cs.raspopov.customer.dto.CustomerDto
import ru.vsu.cs.raspopov.exposed.findThrowableByPredicate
import ru.vsu.cs.raspopov.order.model.dto.request.OrderCreateRequest
import ru.vsu.cs.raspopov.order.model.dto.request.OrderTempUpdateRequest
import ru.vsu.cs.raspopov.order.model.dto.response.OrderTemporaryResponse
import ru.vsu.cs.raspopov.order.model.entity.Order
import ru.vsu.cs.raspopov.order.model.entity.updateTemp
import ru.vsu.cs.raspopov.order.model.enums.OrderStatus
import ru.vsu.cs.raspopov.order.model.mapper.toTemporaryResponse
import ru.vsu.cs.raspopov.order.model.table.Orders
import ru.vsu.cs.raspopov.order.service.IOrderTemporaryService
import ru.vsu.cs.raspopov.orderDetail.service.OrderDetailService
import ru.vsu.cs.raspopov.orderEmployee.service.OrderEmployeeService
import ru.vsu.cs.raspopov.orderWork.service.OrderWorkService

@Service
class OrderTemporaryService(
    private val orderDetailService: OrderDetailService,
    private val orderEmployeeService: OrderEmployeeService,
    private val orderWorkService: OrderWorkService,
) : IOrderTemporaryService {

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
        request: OrderTempUpdateRequest,
        customer: CustomerDto,
    ): OrderTemporaryResponse {
        val temporaryOrder = findThrowableTemporaryOrderById(customer.id)
        temporaryOrder.updateTemp(request)

        return temporaryOrder.toTemporaryResponse()
    }

    internal fun findThrowableTemporaryOrderById(
        customerId: Long,
    ) = Orders.findThrowableByPredicate(GeneralException(ExceptionCode.TEMPORARY_ORDER_NO_CONTENT)) {
        Orders.customerId.eq(customerId) and Orders.status.eq(OrderStatus.TEMPORARY)
    }.let { Order.wrapRow(it) }
}