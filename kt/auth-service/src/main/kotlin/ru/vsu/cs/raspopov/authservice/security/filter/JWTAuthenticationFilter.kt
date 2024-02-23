package ru.vsu.cs.raspopov.authservice.security.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import ru.vsu.cs.raspopov.authservice.security.SecurityProperties
import ru.vsu.cs.raspopov.authservice.service.impl.TokenService
import java.io.IOException

class JWTAuthenticationFilter(
    authenticationManager: AuthenticationManager,

    private val properties: SecurityProperties,
    private val tokenService: TokenService,
) : BasicAuthenticationFilter(authenticationManager) {

    @Throws(IOException::class, ServletException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain
    ) {
        val header = request.getHeader(properties.headerString)
        if (header == null || header.startsWith(properties.tokenPrefix).not()) {
            chain.doFilter(request, response)
            return
        }

        chain.doFilter(request, response)
    }
}