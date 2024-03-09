package ru.vsu.cs.raspopov.coreservice.users.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.boot.info.BuildProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig(
    private val buildProperties: BuildProperties,
) {

//    @Bean
//    fun groupedOpenApi(): GroupedOpenApi {
//        return GroupedOpenApi.builder()
//            .group("user")
//            .packagesToScan("ru/vsu/cs/raspopov/coreservice/users/api")
//            .build()
//    }

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