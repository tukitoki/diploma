package ru.vsu.cs.raspopov.orderEmployee.service

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
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

    fun deleteOrderEmployeeById(
        id: Long,
    ) = OrderWork.findById(id)?.delete()
}