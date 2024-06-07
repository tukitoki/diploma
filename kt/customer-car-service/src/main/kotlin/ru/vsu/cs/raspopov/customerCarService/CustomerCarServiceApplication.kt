package ru.vsu.cs.raspopov.customerCarService

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient(autoRegister = true)
class CustomerCarServiceApplication

fun main(args: Array<String>) {
    runApplication<CustomerCarServiceApplication>(*args)
}
