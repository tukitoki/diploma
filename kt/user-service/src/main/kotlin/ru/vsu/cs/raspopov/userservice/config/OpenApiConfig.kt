package ru.vsu.cs.raspopov.userservice.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.boot.info.BuildProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig(
    private val buildProperties: BuildProperties,
) {

    @Bean
    fun api(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title(buildProperties.name)
                    .version(buildProperties.version)
            )
    }
}