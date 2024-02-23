package ru.vsu.cs.raspopov.authservice.security

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.http.HttpHeaders

@ConfigurationProperties(prefix = "jwt-security")
class SecurityProperties(
    val accessSecretKey: String,
    val refreshSecretKey: String,
    val accessTknTtl: Long,
    val refreshTknTtl: Long,
) {

    val tokenPrefix = "Bearer "
    val headerString = HttpHeaders.AUTHORIZATION
}