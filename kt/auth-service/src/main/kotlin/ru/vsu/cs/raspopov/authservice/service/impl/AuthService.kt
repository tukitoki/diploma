package ru.vsu.cs.raspopov.authservice.service.impl

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import ru.vsu.cs.raspopov.authservice.model.dto.JwtTokens
import ru.vsu.cs.raspopov.authservice.model.dto.request.UserAuthRequest
import ru.vsu.cs.raspopov.authservice.security.AppAuthenticationManager
import ru.vsu.cs.raspopov.authservice.service.IAuthService

@Service
class AuthService(
    private val authenticationManager: AppAuthenticationManager,

    private val tokenService: TokenService,
) : IAuthService {

    override fun login(userAuthRequest: UserAuthRequest): JwtTokens {
        val auth = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                userAuthRequest.username,
                userAuthRequest.password,
            )
        )

        SecurityContextHolder.getContext().authentication = auth

        val refreshToken = tokenService.createRefreshToken(auth)
        val accessToken = tokenService.createAccessToken(refreshToken)

        return JwtTokens(
            tokenService.serializeAccessToken(accessToken),
            tokenService.serializeRefreshToken(refreshToken)
        )
    }

    override fun register() {
        TODO("Not yet implemented")
    }

    override fun logout() {
        TODO("Not yet implemented")
    }

}