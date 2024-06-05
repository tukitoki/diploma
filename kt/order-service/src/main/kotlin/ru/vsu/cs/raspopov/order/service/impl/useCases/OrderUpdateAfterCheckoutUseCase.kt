package ru.vsu.cs.raspopov.order.service.impl.useCases

import org.springframework.stereotype.Service
import ru.vsu.cs.raspopov.client.autoService.dto.messages.OrderUpdateMessage
import ru.vsu.cs.raspopov.client.autoService.producer.OrderMessageProducer
import ru.vsu.cs.raspopov.customer.dto.CustomerDto
import ru.vsu.cs.raspopov.order.model.dto.request.OrderUpdateRequest
import ru.vsu.cs.raspopov.order.model.dto.response.OrderResponse
import ru.vsu.cs.raspopov.order.model.entity.Order
import ru.vsu.cs.raspopov.order.model.entity.updateAfterCheckout
import ru.vsu.cs.raspopov.order.model.mapper.toResponse

@Service
class OrderUpdateAfterCheckoutUseCase(
    private val producer: OrderMessageProducer,
) {

    fun invoke(
        request: OrderUpdateRequest,
        order: Order,
        customer: CustomerDto,
    ): OrderResponse {
        order.updateAfterCheckout(request)
        producer.sendOrderUpdateMessage(OrderUpdateMessage(request))

        return order.toResponse()
    }
}