package ru.vsu.cs.raspopov.authservice.authenticate.service

import ru.vsu.cs.raspopov.authservice.authenticate.dto.request.AuthByOtpRequest
import ru.vsu.cs.raspopov.authservice.tokens.model.dto.JwtTokens
import ru.vsu.cs.raspopov.authservice.authenticate.dto.request.AuthRequest

interface IAuthService {

    fun login(userAuthRequest: AuthRequest): JwtTokens

    fun authByOtpCode(request: AuthByOtpRequest): JwtTokens

    fun register()

    fun logout()
}