//package ru.vsu.cs.raspopov.authservice.configuration
//
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.data.redis.connection.RedisPassword
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
//import org.springframework.data.redis.core.RedisTemplate
//import ru.vsu.cs.raspopov.authservice.model.redis.AccessToken
//import ru.vsu.cs.raspopov.authservice.model.redis.RefreshToken
//import java.util.UUID
//
//@Configuration
//class RedisConfiguration(
//    @Value("\${spring.data.redis.port}")
//    private val redisPort: Int,
//    @Value("\${spring.data.redis.password}")
//    private val redisPassword: String,
//) {
//
//    @Bean
//    fun jedisConnectionFactory(): JedisConnectionFactory {
//        val standaloneConfiguration = RedisStandaloneConfiguration().apply {
//            this.port = redisPort
//            this.password = RedisPassword.of(redisPassword)
//        }
//        val factory = JedisConnectionFactory(standaloneConfiguration)
//        factory.afterPropertiesSet()
//
//        return factory
//    }
//
//    @Bean
//    fun redisAccessTokenTemplate(
//        connectionFactory: JedisConnectionFactory,
//    ) = redisTemplateFactory<AccessToken>(connectionFactory)
//
//    @Bean
//    fun redisRefreshTokenTemplate(
//        connectionFactory: JedisConnectionFactory,
//    ) = redisTemplateFactory<RefreshToken>(connectionFactory)
//
//    private fun <T> redisTemplateFactory(
//        connectionFactory: JedisConnectionFactory,
//    ): RedisTemplate<UUID, T> {
//        val redisTemplate = RedisTemplate<UUID, T>()
//        redisTemplate.connectionFactory = connectionFactory
//
//        return redisTemplate
//    }
//}
