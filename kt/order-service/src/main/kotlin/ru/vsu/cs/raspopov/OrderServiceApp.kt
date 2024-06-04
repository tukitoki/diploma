package ru.vsu.cs.raspopov

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import ru.vsu.cs.raspopov.config.properties.DatabaseProperties

@EnableConfigurationProperties(DatabaseProperties::class)
@SpringBootApplication
class OrderServiceApp

fun main() {
    runApplication<OrderServiceApp>()
}