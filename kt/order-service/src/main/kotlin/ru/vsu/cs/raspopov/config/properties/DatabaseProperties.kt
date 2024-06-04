package ru.vsu.cs.raspopov.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "spring.datasource")
data class DatabaseProperties(
    val url: String,
    val password: String,
    val username: String,
)