package ru.vsu.cs.raspopov.authservice.api

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.vsu.cs.raspopov.authservice.model.dto.JwtTokens

@RestController
class TokenController {

    @PostMapping(REFRESH_JWT_TOKEN)
    fun refreshJwt(): JwtTokens = TODO()

    @PostMapping(VALIDATE_ACCESS_TOKEN)
    fun validateAccessToken(): Nothing = TODO()
}