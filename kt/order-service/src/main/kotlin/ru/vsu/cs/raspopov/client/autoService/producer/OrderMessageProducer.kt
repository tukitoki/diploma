package ru.vsu.cs.raspopov.client.autoService.producer

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import ru.vsu.cs.raspopov.client.autoService.dto.OrderCreateMessage
import ru.vsu.cs.raspopov.config.kafka.SAGA_ORDER_CANCEL_TOPIC
import ru.vsu.cs.raspopov.config.kafka.SAGA_ORDER_CREATE_TOPIC
import ru.vsu.cs.raspopov.config.kafka.SAGA_ORDER_UPDATE_TOPIC
import ru.vsu.cs.raspopov.order.model.dto.request.OrderCancelRequest
import ru.vsu.cs.raspopov.order.model.dto.request.OrderUpdateRequest

@Component
class OrderMessageProducer(
    private val kafkaMessageTemplate: KafkaTemplate<String, OrderCreateMessage>,
    private val kafkaUpdateTemplate: KafkaTemplate<String, OrderUpdateRequest>,
    private val kafkaCancelTemplate: KafkaTemplate<String, OrderCancelRequest>,
) {

    fun sendOrderCreateMessage(message: OrderCreateMessage) {
        kafkaMessageTemplate.send(SAGA_ORDER_CREATE_TOPIC, message)

        // TODO: add logging
    }

    fun sendOrderUpdateMessage(message: OrderUpdateRequest) {
        kafkaUpdateTemplate.send(SAGA_ORDER_UPDATE_TOPIC, message)

        // TODO: add logging
    }

    fun sendOrderCancelMessage(message: OrderCancelRequest) {
        kafkaCancelTemplate.send(SAGA_ORDER_CANCEL_TOPIC, message)

        // TODO: add logging
    }
}