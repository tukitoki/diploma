package ru.vsu.cs.raspopov.authservice.security.filter

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.web.filter.OncePerRequestFilter
import ru.vsu.cs.raspopov.authservice.api.REFRESH_JWT_TOKEN
import ru.vsu.cs.raspopov.authservice.model.redis.RefreshToken
import ru.vsu.cs.raspopov.authservice.model.redis.Token
import ru.vsu.cs.raspopov.authservice.security.SecurityProperties
import ru.vsu.cs.raspopov.authservice.security.token.factory.AbstractTokenFactory
import ru.vsu.cs.raspopov.authservice.security.token.factory.AccessTokenFactory
import ru.vsu.cs.raspopov.authservice.security.token.factory.RefreshTokenFactory
import ru.vsu.cs.raspopov.authservice.security.token.serializer.AbstractTokenStringSerializer
import ru.vsu.cs.raspopov.authservice.security.token.serializer.JweRefreshTokenStringSerializer
import java.nio.file.AccessDeniedException

class RequestJwtTokensFilter(
//    private val authManager: AuthenticationManager,
    private val properties: SecurityProperties,

//    private val refreshTokenFactory: AbstractTokenFactory<Authentication, Token> = RefreshTokenFactory(properties),
//    private val accessTokenFactory: AbstractTokenFactory<RefreshToken, Token> = AccessTokenFactory(properties),

    private val refreshTokenStringSerializer: AbstractTokenStringSerializer<Token>,
    private val accessTokenStringSerializer: AbstractTokenStringSerializer<Token>,

    private val objectMapper: ObjectMapper,
) : OncePerRequestFilter() {

    private val requestMatcher = AntPathRequestMatcher(REFRESH_JWT_TOKEN, HttpMethod.POST.name())

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        if (requestMatcher.matches(request)) {
            val context = SecurityContextHolder.getContext()
            if (context != null && context.authentication !is PreAuthenticatedAuthenticationToken) {

            }
        }

        throw AccessDeniedException("User must be authenticated");
    }
}