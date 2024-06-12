package ru.vsu.cs.raspopov.order.service.impl.useCases

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import ru.vsu.cs.raspopov.client.autoService.dto.messages.OrderCancelMessage
import ru.vsu.cs.raspopov.client.autoService.producer.OrderMessageProducer
import ru.vsu.cs.raspopov.customer.dto.CustomerDto
import ru.vsu.cs.raspopov.order.model.dto.request.OrderCancelRequest
import ru.vsu.cs.raspopov.order.model.entity.Order
import ru.vsu.cs.raspopov.order.model.entity.cancel

@Service
class OrderCancelUseCase(
    private val producer: OrderMessageProducer,
    private val publisher: ApplicationEventPublisher,
) {

    fun invoke(
        request: OrderCancelRequest,
        order: Order,
        customer: CustomerDto,
    ) {
        order.cancel()

        if (order.status.isScheduled()) {
            producer.sendOrderCancelMessage(OrderCancelMessage(request))
        }
    }
}