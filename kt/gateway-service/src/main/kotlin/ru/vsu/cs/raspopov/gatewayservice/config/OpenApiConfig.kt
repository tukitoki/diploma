package ru.vsu.cs.raspopov.gatewayservice.config

import org.springdoc.core.properties.SwaggerUiConfigParameters
import org.springframework.boot.CommandLineRunner
import org.springframework.cloud.gateway.route.RouteDefinition
import org.springframework.cloud.gateway.route.RouteDefinitionLocator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.Objects


@Configuration
class OpenApiConfig {

    @Bean
    fun commandLiner(
        locator: RouteDefinitionLocator,
        swaggerUiParam: SwaggerUiConfigParameters
    ) = CommandLineRunner { args ->
        Objects.requireNonNull(
            locator.routeDefinitions.collectList().block()
        )
            .map { obj -> obj.id }
            .filter { id -> id.matches(Regex(".*-service")) }
            .map { id -> id.replace("-service", "") }
            .forEach { swaggerUiParam.addGroup(it) }
    }
}