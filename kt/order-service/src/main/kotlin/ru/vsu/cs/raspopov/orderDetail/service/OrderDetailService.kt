package ru.vsu.cs.raspopov.orderDetail.service

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Op
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.vsu.cs.raspopov.common.exception.ExceptionCode
import ru.vsu.cs.raspopov.common.exception.GeneralException
import ru.vsu.cs.raspopov.exposed.findThrowableById
import ru.vsu.cs.raspopov.order.model.entity.Order
import ru.vsu.cs.raspopov.orderDetail.model.dto.request.OrderDetailCreateRequest
import ru.vsu.cs.raspopov.orderDetail.model.entity.OrderDetail
import ru.vsu.cs.raspopov.orderDetail.model.mapper.toResponse
import ru.vsu.cs.raspopov.orderDetail.model.table.OrderDetails

@Transactional
@Service
class OrderDetailService {

    fun createOrderDetail(
        request: OrderDetailCreateRequest,
        orderId: EntityID<Long>,
    ) = OrderDetail.new {
        this.detailId = request.detailId
        this.price = request.price.toDouble()
        this.orderId = orderId
    }.toResponse()

    fun getDetailById(
        id: Long,
    ) = findThrowableOrderDetailById(id, Op.nullOp())

    fun deleteOrderDetailById(
        id: Long,
    ) = OrderDetail.findById(id)?.delete()

    private fun findThrowableOrderDetailById(
        id: Long,
        predicate: Op<Boolean>,
    ) = OrderDetails.findThrowableById(id, GeneralException(ExceptionCode.ORDER_DETAIL_NOT_FOUND_BY_ID), predicate)
        .let { Order.wrapRow(it) }
}