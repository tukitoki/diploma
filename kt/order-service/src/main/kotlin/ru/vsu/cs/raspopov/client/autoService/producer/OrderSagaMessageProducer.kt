package ru.vsu.cs.raspopov.client.autoService.producer

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component
import ru.vsu.cs.raspopov.client.autoService.dto.OrderSagaResponse
import ru.vsu.cs.raspopov.config.kafka.SAGA_ORDER_RESPONSE_TOPIC

@Component
class OrderSagaMessageProducer(
    private val kafkaTemplate: KafkaTemplate<String, OrderSagaResponse>,
) {

    fun sendOrderSagaResultResponse(response: OrderSagaResponse, ack: Acknowledgment) {
        kafkaTemplate.send(SAGA_ORDER_RESPONSE_TOPIC, response)

        ack.acknowledge()

        // TODO: add logger
    }
}