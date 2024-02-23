package ru.vsu.cs.raspopov.authservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import ru.vsu.cs.raspopov.authservice.security.SecurityProperties

@SpringBootApplication
@EnableConfigurationProperties(SecurityProperties::class)
class AuthServiceApplication

fun main(args: Array<String>) {
    runApplication<AuthServiceApplication>(*args)
}
