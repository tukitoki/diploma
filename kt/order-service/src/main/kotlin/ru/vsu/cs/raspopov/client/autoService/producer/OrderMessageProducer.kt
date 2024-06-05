package ru.vsu.cs.raspopov.client.autoService.producer

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import ru.vsu.cs.raspopov.client.autoService.dto.messages.OrderCancelMessage
import ru.vsu.cs.raspopov.client.autoService.dto.messages.OrderCreateMessage
import ru.vsu.cs.raspopov.client.autoService.dto.messages.OrderUpdateMessage
import ru.vsu.cs.raspopov.config.kafka.SAGA_ORDER_CANCEL_TOPIC
import ru.vsu.cs.raspopov.config.kafka.SAGA_ORDER_CREATE_TOPIC
import ru.vsu.cs.raspopov.config.kafka.SAGA_ORDER_UPDATE_TOPIC

@Component
class OrderMessageProducer(
    private val kafkaMessageTemplate: KafkaTemplate<String, OrderCreateMessage>,
    private val kafkaUpdateTemplate: KafkaTemplate<String, OrderUpdateMessage>,
    private val kafkaCancelTemplate: KafkaTemplate<String, OrderCancelMessage>,
) {

    fun sendOrderCreateMessage(message: OrderCreateMessage) {
        kafkaMessageTemplate.send(SAGA_ORDER_CREATE_TOPIC, message)

        // TODO: add logging
    }

    fun sendOrderUpdateMessage(message: OrderUpdateMessage) {
        kafkaUpdateTemplate.send(SAGA_ORDER_UPDATE_TOPIC, message)

        // TODO: add logging
    }

    fun sendOrderCancelMessage(message: OrderCancelMessage) {
        kafkaCancelTemplate.send(SAGA_ORDER_CANCEL_TOPIC, message)

        // TODO: add logging
    }
}