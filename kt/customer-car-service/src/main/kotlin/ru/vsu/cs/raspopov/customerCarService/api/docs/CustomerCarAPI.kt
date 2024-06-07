package ru.vsu.cs.raspopov.customerCarService.api.docs

import org.springframework.http.ResponseEntity
import ru.vsu.cs.raspopov.customer.dto.CustomerDto
import ru.vsu.cs.raspopov.customerCarService.model.dto.request.CreateCarRequest
import ru.vsu.cs.raspopov.customerCarService.model.dto.request.MakeCarPrimaryRequest
import ru.vsu.cs.raspopov.customerCarService.model.dto.response.CustomerCarResponse

interface CustomerCarAPI {

    fun getAllCars(customerDto: CustomerDto): ResponseEntity<Collection<CustomerCarResponse>>

    fun getCarById(customerDto: CustomerDto, id: Long): ResponseEntity<CustomerCarResponse>

    fun addNewCar(customerDto: CustomerDto, request: CreateCarRequest): ResponseEntity<CustomerCarResponse>

    fun deleteCarById(customerDto: CustomerDto, id: Long): ResponseEntity<Unit>

    fun makeCarPrimary(customerDto: CustomerDto, request: MakeCarPrimaryRequest): ResponseEntity<CustomerCarResponse>
}