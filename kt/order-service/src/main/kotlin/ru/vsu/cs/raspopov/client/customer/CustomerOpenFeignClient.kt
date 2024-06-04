package ru.vsu.cs.raspopov.client.customer

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import ru.vsu.cs.raspopov.client.customer.dto.CustomerByUserRequest
import ru.vsu.cs.raspopov.customer.dto.CustomerDto

@FeignClient("customer-service")
interface CustomerOpenFeignClient {

    @RequestMapping(method = [RequestMethod.POST], value = ["/api/customer"])
    fun getCustomerByUserId(request: CustomerByUserRequest): CustomerDto
}