package ru.vsu.cs.raspopov.customerCarService.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.vsu.cs.raspopov.customer.dto.CustomerDto
import ru.vsu.cs.raspopov.customerCarService.api.docs.CustomerCarAPI
import ru.vsu.cs.raspopov.customerCarService.model.dto.request.CreateCarRequest
import ru.vsu.cs.raspopov.customerCarService.model.dto.request.MakeCarPrimaryRequest
import ru.vsu.cs.raspopov.customerCarService.model.dto.response.CustomerCarResponse

@RequestMapping("/api/customer-cars")
@RestController
class CustomerCarController : CustomerCarAPI {

    @GetMapping
    override fun getAllCars(
        customerDto: CustomerDto,
    ): ResponseEntity<Collection<CustomerCarResponse>> {
        TODO("Not yet implemented")
    }

    @GetMapping("/{id}")
    override fun getCarById(
        customerDto: CustomerDto,
        @PathVariable id: Long
    ): ResponseEntity<CustomerCarResponse> {
        TODO("Not yet implemented")
    }

    @PostMapping
    override fun addNewCar(
        customerDto: CustomerDto,
        @RequestBody request: CreateCarRequest,
    ): ResponseEntity<CustomerCarResponse> {
        TODO("Not yet implemented")
    }

    @DeleteMapping("/{id}")
    override fun deleteCarById(
        customerDto: CustomerDto,
        @PathVariable id: Long,
    ): ResponseEntity<Unit> {
        TODO("Not yet implemented")
    }

    @PatchMapping("/make-primary")
    override fun makeCarPrimary(
        customerDto: CustomerDto,
        @RequestBody request: MakeCarPrimaryRequest,
    ): ResponseEntity<CustomerCarResponse> {
        TODO("Not yet implemented")
    }
}