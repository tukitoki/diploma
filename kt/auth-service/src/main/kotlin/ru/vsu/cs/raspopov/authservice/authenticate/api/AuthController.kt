package ru.vsu.cs.raspopov.authservice.authenticate.api

import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.vsu.cs.raspopov.authservice.api.AUTH_BY_OTP_PATH
import ru.vsu.cs.raspopov.authservice.api.LOGIN_PATH
import ru.vsu.cs.raspopov.authservice.api.LOGOUT
import ru.vsu.cs.raspopov.authservice.authenticate.dto.request.AuthByOtpRequest
import ru.vsu.cs.raspopov.authservice.tokens.model.dto.JwtTokens
import ru.vsu.cs.raspopov.authservice.authenticate.dto.request.AuthRequest
import ru.vsu.cs.raspopov.authservice.authenticate.service.IAuthService

@RestController
class AuthController(
    private val authService: IAuthService,
) {

    @PostMapping(LOGIN_PATH)
    fun authenticate(
        @RequestBody authRequest: AuthRequest,
    ): ResponseEntity<JwtTokens> {

        return ok(authService.login(authRequest))
    }

    @PostMapping(AUTH_BY_OTP_PATH)
    fun authenticateByOtp(
        @RequestBody request: AuthByOtpRequest,
    ): ResponseEntity<JwtTokens> {

        return ok(authService.authByOtpCode(request))
    }

    @PostMapping(LOGOUT)
    fun logout(): Nothing = TODO()

}