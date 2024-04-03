package ru.vsu.cs.raspopov.authservice.api

import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import ru.vsu.cs.raspopov.authservice.model.dto.JwtTokens
import ru.vsu.cs.raspopov.authservice.model.dto.TokenParseResponse
import ru.vsu.cs.raspopov.authservice.model.dto.TokenValidationOutput
import ru.vsu.cs.raspopov.authservice.service.impl.TokenService

@RestController
class TokenController(
    private val tokenService: TokenService,
) {

    @PostMapping(REFRESH_JWT_TOKEN)
    fun refreshJwt(): JwtTokens = TODO()

    @PostMapping(VALIDATE_ACCESS_TOKEN)
    fun validateAccessToken(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION) token: String,
    ): ResponseEntity<TokenValidationOutput> {

        return ok(tokenService.validateSerializedAccessToken(token))
    }

    @PostMapping
    fun parseToken(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION) token: String,
    ): ResponseEntity<TokenParseResponse> {

        return ok(tokenService.parseToken(token))
    }
}