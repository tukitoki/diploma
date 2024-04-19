package ru.vsu.cs.raspopov.authservice.api

import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.vsu.cs.raspopov.authservice.model.dto.JwtTokens
import ru.vsu.cs.raspopov.authservice.model.dto.request.UserAuthRequest
import ru.vsu.cs.raspopov.authservice.service.IAuthService

@RestController
class AuthController(
    private val authService: IAuthService,
) {

    @PostMapping(LOGIN_PATH)
    fun login(
        @RequestBody authRequest: UserAuthRequest,
    ): ResponseEntity<JwtTokens> {

        return ok(authService.login(authRequest))
    }

    @PostMapping(REGISTER)
    fun register(): Nothing = TODO()

    @PostMapping(LOGOUT)
    fun logout(): Nothing = TODO()

}