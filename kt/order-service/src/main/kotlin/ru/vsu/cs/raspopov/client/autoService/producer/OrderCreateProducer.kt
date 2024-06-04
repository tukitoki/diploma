package ru.vsu.cs.raspopov.client.autoService.producer

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import ru.vsu.cs.raspopov.client.autoService.dto.OrderCreateMessage
import ru.vsu.cs.raspopov.config.kafka.SAGA_AUTO_SERVICE_PRODUCE_TOPIC

@Component
class OrderCreateProducer(
    private val kafkaMessageTemplate: KafkaTemplate<String, OrderCreateMessage>
) {

    fun sendOrderCreateMessage(message: OrderCreateMessage) {
        kafkaMessageTemplate.send(SAGA_AUTO_SERVICE_PRODUCE_TOPIC, message)

        // TODO: add logging
    }
}