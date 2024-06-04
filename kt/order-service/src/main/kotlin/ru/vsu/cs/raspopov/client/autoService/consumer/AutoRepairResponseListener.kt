package ru.vsu.cs.raspopov.client.autoService.consumer

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component
import ru.vsu.cs.raspopov.client.autoService.dto.AutoServiceResponse
import ru.vsu.cs.raspopov.client.autoService.dto.PerformOperation
import ru.vsu.cs.raspopov.config.kafka.SAGA_ORDER_LISTEN_TOPIC
import ru.vsu.cs.raspopov.order.service.impl.useCases.result.OrderCheckoutResultUseCase
import ru.vsu.cs.raspopov.order.service.impl.useCases.result.OrderConfirmResultUseCase
import ru.vsu.cs.raspopov.order.service.impl.useCases.result.OrderUpdateResultUseCase

@Component
class AutoRepairResponseListener(
    private val checkoutResultUseCase: OrderCheckoutResultUseCase,
    private val updateResultUseCase: OrderUpdateResultUseCase,
    private val orderConfirmResultUseCase: OrderConfirmResultUseCase,
) {

    @KafkaListener(
        groupId = "order-group",
        topics = [SAGA_ORDER_LISTEN_TOPIC],
        containerFactory = "autoRepairListenerFactory"
    )
    fun listenAutoRepairResponse(response: AutoServiceResponse, ack: Acknowledgment) {
        when (response.performOperation) {
            PerformOperation.CHECKOUT -> checkoutResultUseCase.invoke(response)

            PerformOperation.UPDATE -> updateResultUseCase.invoke(response)

            PerformOperation.CONFIRM -> orderConfirmResultUseCase.invoke(response)
        }

        ack.acknowledge()

        // TODO: add logging
    }
}