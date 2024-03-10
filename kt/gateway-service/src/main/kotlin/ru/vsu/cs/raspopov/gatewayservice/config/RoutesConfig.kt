package ru.vsu.cs.raspopov.gatewayservice.config

import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec
import org.springframework.cloud.gateway.route.builder.PredicateSpec
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.route.builder.filters
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.web.reactive.function.client.WebClient
import ru.vsu.cs.raspopov.gatewayservice.filter.AuthenticationFilter


@Configuration
class RoutesConfig(
    private val webClientBuilder: WebClient.Builder,
) {

    @Bean
    fun routeLocator(builder: RouteLocatorBuilder): RouteLocator {

        return builder.routes()
            .route(
                "user-service-auth"
            ) { r: PredicateSpec ->
                r.path(
                    "/user-service/**"
                ).filters { f: GatewayFilterSpec ->
                    f.filter { exchange, chain ->
                        if (exchange.request.headers.containsKey(HttpHeaders.AUTHORIZATION).not()) {
                            TODO()
                        }

                        val authHeader = exchange.request.headers[HttpHeaders.AUTHORIZATION]?.get(0)
                            ?: TODO()

                        val token = authHeader.substringAfter(AuthenticationFilter.AUTH_PREFIX)

                        webClientBuilder.build()
                            .post()
                            .uri("http://localhost:8082/auth-service/validate")
                            .headers { it.setBearerAuth(token) }
                            .retrieve()
                            .toBodilessEntity()
                            .flatMap { chain.filter(exchange) }
                    }
                }
                    .uri("lb://user-service")
            }
            .build()
    }
}