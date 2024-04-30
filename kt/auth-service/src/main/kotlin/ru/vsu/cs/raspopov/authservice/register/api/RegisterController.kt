package ru.vsu.cs.raspopov.authservice.register.api

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import ru.vsu.cs.raspopov.authservice.api.REGISTER
import ru.vsu.cs.raspopov.authservice.register.service.RegisterService

@RestController
class RegisterController(
    private val service: RegisterService,
) {

    @PostMapping(REGISTER)
    fun register(): Nothing = TODO()
}