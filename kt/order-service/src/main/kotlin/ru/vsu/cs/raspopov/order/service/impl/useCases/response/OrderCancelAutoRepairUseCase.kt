package ru.vsu.cs.raspopov.order.service.impl.useCases.response

import org.jetbrains.exposed.sql.Op
import org.springframework.stereotype.Service
import ru.vsu.cs.raspopov.client.autoService.dto.AutoServiceCancelResponse
import ru.vsu.cs.raspopov.client.autoService.dto.OrderSagaResponse
import ru.vsu.cs.raspopov.client.autoService.producer.OrderSagaMessageProducer
import ru.vsu.cs.raspopov.order.model.entity.cancel
import ru.vsu.cs.raspopov.order.service.impl.OrderService

@Service
class OrderCancelAutoRepairUseCase(
    private val orderService: OrderService,
    private val orderSagaMessageProducer: OrderSagaMessageProducer,
) {

    fun invoke(
        response: AutoServiceCancelResponse
    ) = runCatching {
        val cancellingOrder = orderService.findThrowableOrderById(response.orderId, Op.nullOp())

        cancellingOrder.cancel()
    }.onFailure {
        TODO()
    }
}