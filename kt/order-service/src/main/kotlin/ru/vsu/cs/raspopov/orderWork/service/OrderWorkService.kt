package ru.vsu.cs.raspopov.orderWork.service

import org.jetbrains.exposed.dao.id.EntityID
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.vsu.cs.raspopov.orderWork.model.dto.request.OrderWorkCreateRequest
import ru.vsu.cs.raspopov.orderWork.model.entity.OrderWork
import ru.vsu.cs.raspopov.orderWork.model.mapper.toResponse

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
}