package ru.vsu.cs.raspopov.orderDetail.service

import org.jetbrains.exposed.dao.id.EntityID
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.vsu.cs.raspopov.orderDetail.model.dto.request.OrderDetailCreateRequest
import ru.vsu.cs.raspopov.orderDetail.model.entity.OrderDetail
import ru.vsu.cs.raspopov.orderDetail.model.mapper.toResponse

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
}