package ru.vsu.cs.raspopov.authservice.service

import ru.vsu.cs.raspopov.authservice.model.dto.JwtTokens
import ru.vsu.cs.raspopov.authservice.model.dto.UserAuthRequest

interface IAuthService {

    fun login(userAuthRequest: UserAuthRequest): JwtTokens

    fun register()

    fun logout()
}