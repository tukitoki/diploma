package ru.vsu.cs.raspopov.orderEmployee.service

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Op
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.vsu.cs.raspopov.common.exception.ExceptionCode
import ru.vsu.cs.raspopov.common.exception.GeneralException
import ru.vsu.cs.raspopov.exposed.findThrowableById
import ru.vsu.cs.raspopov.order.model.entity.Order
import ru.vsu.cs.raspopov.orderEmployee.model.dto.request.OrderEmployeeCreateRequest
import ru.vsu.cs.raspopov.orderEmployee.model.entity.OrderEmployee
import ru.vsu.cs.raspopov.orderEmployee.model.mapper.toResponse
import ru.vsu.cs.raspopov.orderEmployee.model.table.OrderEmployees
import ru.vsu.cs.raspopov.orderWork.model.entity.OrderWork

@Transactional
@Service
class OrderEmployeeService {

    fun createOrderEmployee(
        request: OrderEmployeeCreateRequest,
        orderId: EntityID<Long>,
    ) = OrderEmployee.new {
        this.employeeId = request.employeeId
        this.orderId = orderId
    }.toResponse()

    fun getEmployeeById(
        id: Long,
    ) = findThrowableOrderEmployeeById(id, Op.nullOp())

    fun deleteOrderEmployeeById(
        id: Long,
    ) = OrderWork.findById(id)?.delete()

    private fun findThrowableOrderEmployeeById(
        id: Long,
        predicate: Op<Boolean>,
    ) = OrderEmployees.findThrowableById(id, GeneralException(ExceptionCode.ORDER_EMPLOYEE_NOT_FOUND_BY_ID), predicate)
        .let { Order.wrapRow(it) }
}