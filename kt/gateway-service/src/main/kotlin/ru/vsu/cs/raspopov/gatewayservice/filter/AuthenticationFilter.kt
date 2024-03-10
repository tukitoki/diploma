package ru.vsu.cs.raspopov.gatewayservice.filter

import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class AuthenticationFilter(
    private val webClientBuilder: WebClient.Builder,
) : AbstractGatewayFilterFactory<Any>() {

    override fun apply(config: Any): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            if (exchange.request.headers.containsKey(HttpHeaders.AUTHORIZATION).not()) {
                TODO()
            }

            val authHeader = exchange.request.headers[HttpHeaders.AUTHORIZATION]?.get(0)
                ?: TODO()

            val token = authHeader.substringAfter(AUTH_PREFIX)

            webClientBuilder.build()
                .post()
                .uri("http://localhost:8082/auth-service/validate")
                .headers { it.setBearerAuth(token) }
                .retrieve()
                .toBodilessEntity()
                .doOnError {
                    print(it)
                    print(it)
                }
                .flatMap { chain.filter(exchange) }

        }
    }

    class Config {

    }

    companion object {
        const val AUTH_PREFIX = "Bearer"
    }
}