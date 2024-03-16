package ru.vsu.cs.raspopov.gatewayservice.config

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec
import org.springframework.cloud.gateway.route.builder.PredicateSpec
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import ru.vsu.cs.raspopov.gatewayservice.filter.AuthenticationFilter


@Configuration
class RoutesConfig(
    private val webClientBuilder: WebClient.Builder,
) {

    @Bean
    fun routeLocator(
        builder: RouteLocatorBuilder,
        authenticationFilter: AuthenticationFilter,
    ): RouteLocator {

        return builder.routes()
            .route(
                "user-service-auth"
            ) { r: PredicateSpec ->
                r.path(
                    "/user-service/**"
                ).filters { f: GatewayFilterSpec ->
                    f.filter(authenticationFilter.apply { AuthenticationFilter.Config() })
                    f.rewritePath("/user-service/(?<segment>.*)", "/\${segment}")
                }
                    .uri("lb://user-service")
            }
            .build()
    }
}