package ru.vsu.cs.raspopov.client.autoService.consumer

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component
import ru.vsu.cs.raspopov.client.autoService.dto.AutoServiceResponse
import ru.vsu.cs.raspopov.config.kafka.SAGA_AUTO_SERVICE_PRODUCE_TOPIC
import ru.vsu.cs.raspopov.order.service.impl.useCases.OrderCheckoutResultUseCase

@Component
class AutoRepairResponseListener(
    private val checkoutResultUseCase: OrderCheckoutResultUseCase,
) {

    @KafkaListener(
        groupId = "order-group",
        topics = [SAGA_AUTO_SERVICE_PRODUCE_TOPIC],
        containerFactory = "autoRepairListenerFactory"
    )
    fun listenAutoRepairResponse(response: AutoServiceResponse, ack: Acknowledgment) {
        checkoutResultUseCase.invoke(response)

        ack.acknowledge()

        // TODO: add logging
    }
}