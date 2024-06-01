package ru.vsu.cs.raspopov.authservice.tokens.api

import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.vsu.cs.raspopov.authservice.api.REFRESH_JWT_TOKEN
import ru.vsu.cs.raspopov.authservice.api.VALIDATE_ACCESS_TOKEN
import ru.vsu.cs.raspopov.authservice.tokens.model.dto.JwtTokens
import ru.vsu.cs.raspopov.authservice.tokens.model.dto.response.TokenParseResponse
import ru.vsu.cs.raspopov.authservice.tokens.model.dto.response.TokenValidationResponse
import ru.vsu.cs.raspopov.authservice.tokens.service.impl.TokenService

@RequestMapping("/api/token")
@RestController
class TokenController(
    private val tokenService: TokenService,
) {

    @PostMapping(REFRESH_JWT_TOKEN)
    fun refreshJwt(): JwtTokens = TODO()

    @PostMapping(VALIDATE_ACCESS_TOKEN)
    fun validateAccessToken(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION) token: String,
    ): ResponseEntity<TokenValidationResponse> {

        return ok(tokenService.validateSerializedAccessToken(token))
    }

    @PostMapping
    fun parseToken(
        @RequestHeader(value = HttpHeaders.AUTHORIZATION) token: String,
    ): ResponseEntity<TokenParseResponse> {

        return ok(tokenService.parseToken(token))
    }
}