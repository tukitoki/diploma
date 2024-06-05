package ru.vsu.cs.raspopov.orderWork.service

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Op
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.vsu.cs.raspopov.common.exception.ExceptionCode
import ru.vsu.cs.raspopov.common.exception.GeneralException
import ru.vsu.cs.raspopov.exposed.findThrowableById
import ru.vsu.cs.raspopov.order.model.entity.Order
import ru.vsu.cs.raspopov.orderWork.model.dto.request.OrderWorkCreateRequest
import ru.vsu.cs.raspopov.orderWork.model.entity.OrderWork
import ru.vsu.cs.raspopov.orderWork.model.mapper.toResponse
import ru.vsu.cs.raspopov.orderWork.model.table.OrderWorks

@Transactional
@Service
class OrderWorkService {

    fun createOrderWork(
        request: OrderWorkCreateRequest,
        orderId: EntityID<Long>,
    ) = OrderWork.new {
        this.workId = request.workId
        this.price = request.price.toDouble()
        this.orderId = orderId
    }.toResponse()

    fun getOrderWorkById(
        id: Long,
    ) = findThrowableOrderWorkById(id, Op.nullOp())

    fun deleteOrderWorkById(
        id: Long,
    ) = OrderWork.findById(id)?.delete()

    private fun findThrowableOrderWorkById(
        id: Long,
        predicate: Op<Boolean>,
    ) = OrderWorks.findThrowableById(id, GeneralException(ExceptionCode.ORDER_EMPLOYEE_NOT_FOUND_BY_ID), predicate)
        .let { Order.wrapRow(it) }
}