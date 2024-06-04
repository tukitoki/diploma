package ru.vsu.cs.raspopov.order.service.impl.useCases

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.vsu.cs.raspopov.client.autoService.dto.OrderCreateMessage
import ru.vsu.cs.raspopov.client.autoService.producer.OrderCreateProducer
import ru.vsu.cs.raspopov.customer.dto.CustomerDto
import ru.vsu.cs.raspopov.order.model.dto.request.OrderCheckoutRequest
import ru.vsu.cs.raspopov.order.model.dto.response.OrderResponse
import ru.vsu.cs.raspopov.order.model.entity.Order
import ru.vsu.cs.raspopov.order.model.entity.checkout

@Transactional
@Service
class OrderCheckoutUseCase(
    private val producer: OrderCreateProducer,
) {

    fun invoke(
        request: OrderCheckoutRequest,
        temporaryOrder: Order,
        customer: CustomerDto,
    ): OrderResponse {
        temporaryOrder.checkout()

        val message = OrderCreateMessage(temporaryOrder)
        producer.sendOrderCreateMessage(message)

        TODO("impl")
    }
}