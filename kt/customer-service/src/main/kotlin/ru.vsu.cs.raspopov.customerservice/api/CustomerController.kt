package ru.vsu.cs.raspopov.customerservice.api

import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.vsu.cs.raspopov.customerservice.api.docs.CustomerAPI
import ru.vsu.cs.raspopov.customerservice.model.dto.request.CustomerCreateRequest
import ru.vsu.cs.raspopov.customerservice.model.dto.response.CustomerResponse
import ru.vsu.cs.raspopov.customerservice.service.ICustomerService

@RequestMapping("/api/customers")
@RestController
class CustomerController(
    private val customerService: ICustomerService,
) : CustomerAPI {

    @PostMapping
    override fun createCustomer(
        @RequestBody request: CustomerCreateRequest
    ): ResponseEntity<CustomerResponse> {

        return ok(customerService.createCustomer(request))
    }

    @GetMapping("/{id}")
    override fun getCustomerById(
        @PathVariable id: Long
    ): ResponseEntity<CustomerResponse> {

        return ok(customerService.getCustomerById(id))
    }


}