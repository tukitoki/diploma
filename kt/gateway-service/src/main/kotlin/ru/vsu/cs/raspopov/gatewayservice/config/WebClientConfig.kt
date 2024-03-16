package ru.vsu.cs.raspopov.gatewayservice.config

import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig {

    @Bean
    @LoadBalanced
    fun loadBalancedWebClient(
    ): WebClient.Builder = WebClient.builder()
}