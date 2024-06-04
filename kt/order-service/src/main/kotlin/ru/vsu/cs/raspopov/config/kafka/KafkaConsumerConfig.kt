package ru.vsu.cs.raspopov.config.kafka

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.listener.CommonErrorHandler
import org.springframework.kafka.listener.CommonLoggingErrorHandler
import org.springframework.kafka.listener.ContainerProperties
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.kafka.transaction.KafkaTransactionManager
import ru.vsu.cs.raspopov.client.autoService.dto.AutoServiceResponse

@Configuration
class KafkaConsumerConfig(
    @Value("\${spring.kafka.bootstrap-servers}")
    private val bootstrapServers: String,
) {

    @Bean
    fun errorHandler() = CommonLoggingErrorHandler()

    @Bean
    fun autoRepairListenerFactory(
        errorHandler: CommonErrorHandler,
        kafkaTransactionManager: KafkaTransactionManager<String, AutoServiceResponse>,
    ) = listenerFactory(AutoServiceResponse::class.java, errorHandler, kafkaTransactionManager)

    private fun <T> listenerFactory(
        clazz: Class<T>,
        errorHandler: CommonErrorHandler,
        kafkaTransactionManager: KafkaTransactionManager<String, T>,
    ): ConcurrentKafkaListenerContainerFactory<String, T> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, T>()
        factory.consumerFactory = consumerFactory(clazz)
        factory.containerProperties.ackMode = ContainerProperties.AckMode.MANUAL_IMMEDIATE
        factory.setCommonErrorHandler(errorHandler)
        factory.containerProperties.kafkaAwareTransactionManager = kafkaTransactionManager

        return factory
    }

    private fun <T> consumerFactory(
        clazz: Class<T>,
    ) = DefaultKafkaConsumerFactory<String, T>(
        consumerConfig,
        StringDeserializer(),
        ErrorHandlingDeserializer(JsonDeserializer(clazz))
    )

    private val consumerConfig = mapOf(
        ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServers,
        ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to ErrorHandlingDeserializer::class.java,
        ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to ErrorHandlingDeserializer::class.java,
        ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG to false,
        ConsumerConfig.ISOLATION_LEVEL_CONFIG to "read_committed",

        ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS to StringDeserializer::class.java,
        ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS to JsonDeserializer::class.java,

        JsonDeserializer.TRUSTED_PACKAGES to "*",
//        JsonDeserializer.TYPE_MAPPINGS to """
//            *
//        """.trimIndent(),
    )
}