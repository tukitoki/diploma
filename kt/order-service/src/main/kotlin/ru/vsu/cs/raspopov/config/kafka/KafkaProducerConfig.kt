package ru.vsu.cs.raspopov.config.kafka

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.serializer.JsonSerializer
import org.springframework.kafka.transaction.KafkaTransactionManager
import java.util.*

@Configuration
class KafkaProducerConfig(
    @Value("\${spring.kafka.bootstrap-servers}")
    private val bootstrapServers: String,
    @Value("\${spring.application.name}")
    private val appName: String,
) {

    @Bean
    fun <T> producerFactory(): DefaultKafkaProducerFactory<String, T> {
        val factory = DefaultKafkaProducerFactory<String, T>(producerConfig)
        factory.setTransactionIdPrefix("${appName}-${UUID.randomUUID()}-")

        return factory
    }

    @Bean
    fun <T> kafkaTransactionManager(
        producerFactory: ProducerFactory<String, T>,
    ): KafkaTransactionManager<String, T> {
        return KafkaTransactionManager(producerFactory)
    }

    private val producerConfig = mapOf(
        ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServers,
        ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
        ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to JsonSerializer::class.java,
        ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG to true,
        ProducerConfig.TRANSACTIONAL_ID_CONFIG to "tr-id-1",
    )
}