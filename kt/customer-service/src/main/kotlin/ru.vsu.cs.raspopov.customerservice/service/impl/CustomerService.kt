package ru.vsu.cs.raspopov.customerservice.service.impl

import org.springframework.stereotype.Service
import ru.vsu.cs.raspopov.customerservice.model.dto.request.CustomerCreateRequest
import ru.vsu.cs.raspopov.customerservice.model.dto.response.CustomerResponse
import ru.vsu.cs.raspopov.customerservice.model.entity.Customer
import ru.vsu.cs.raspopov.customerservice.model.mapper.toResponse
import ru.vsu.cs.raspopov.customerservice.model.table.Customers
import ru.vsu.cs.raspopov.customerservice.service.ICustomerService
import ru.vsu.cs.raspopov.exposed.findThrowableById
import java.lang.RuntimeException

@Service
class CustomerService : ICustomerService {

    override fun createCustomer(
        request: CustomerCreateRequest,
    ): CustomerResponse {
        TODO("call user service to create user, then create customer")
    }

    override fun getCustomerById(
        id: Long,
    ) = findThrowableCustomerById(id)
        .let { Customer.wrapRow(it) }
        .toResponse()

    private fun findThrowableCustomerById(
        id: Long,
    ) = Customers.findThrowableById(id, RuntimeException())
}