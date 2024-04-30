package ru.vsu.cs.raspopov.authservice.authenticate.service.impl

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import ru.vsu.cs.raspopov.authservice.authenticate.dto.request.AuthByOtpRequest
import ru.vsu.cs.raspopov.authservice.tokens.model.dto.JwtTokens
import ru.vsu.cs.raspopov.authservice.authenticate.dto.request.AuthRequest
import ru.vsu.cs.raspopov.authservice.authenticate.service.IAuthService
import ru.vsu.cs.raspopov.authservice.sms.model.dto.request.ValidateOtpCodeRequest
import ru.vsu.cs.raspopov.authservice.sms.service.IRequestCodeService
import ru.vsu.cs.raspopov.authservice.tokens.service.impl.TokenService

@Service
class AuthService(
    @Qualifier("appAuthenticationManager")
    private val authenticationManager: AuthenticationManager,
    @Qualifier("otpCodeAuthenticationManager")
    private val otpCodeAuthenticationManager: AuthenticationManager,

    private val tokenService: TokenService,
    private val requestCodeService: IRequestCodeService,
) : IAuthService {

    override fun login(userAuthRequest: AuthRequest): JwtTokens {
        val auth = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                userAuthRequest.username,
                userAuthRequest.password,
            )
        )

        return generateTokens(auth)
    }

    override fun authByOtpCode(request: AuthByOtpRequest): JwtTokens {
        requestCodeService.validateRequestCode(
            ValidateOtpCodeRequest(request.phoneNumber, request.code)
        )

        val auth = otpCodeAuthenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                request.phoneNumber,
                request.code,
            )
        )

        return generateTokens(auth)
    }

    override fun register() {
        TODO("Not yet implemented")
    }

    override fun logout() {
        TODO("Not yet implemented")
    }

    private fun generateTokens(auth: Authentication): JwtTokens {
        SecurityContextHolder.getContext().authentication = auth

        val refreshToken = tokenService.createRefreshToken(auth)
        val accessToken = tokenService.createAccessToken(refreshToken)

        return JwtTokens(
            tokenService.serializeAccessToken(accessToken),
            tokenService.serializeRefreshToken(refreshToken)
        )
    }
}