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
class RoutesConfig {

    @Bean
    fun routeLocator(
        builder: RouteLocatorBuilder,
        authenticationFilter: AuthenticationFilter,
    ): RouteLocator {

        return builder.routes()
            .route("user-service-auth") { r ->
                r.path(
                    "/user-service/**"
                ).filters { f ->
                    f.filter(authenticationFilter.apply { AuthenticationFilter.Config() })
                    f.rewritePath("/user-service/(?<segment>.*)", "/\${segment}")
                }
                    .uri("lb://user-service")
            }
            .route("auth-service") { r ->
                r.path("/auth-service/**")
                    .filters { f ->
                        f.rewritePath("/auth-service/(?<segment>.*)", "/\${segment}")
                    }
                    .uri("lb://auth-service")
            }
            .build()
    }
}