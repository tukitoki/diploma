package ru.vsu.cs.raspopov.authservice.security.config

import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationConverter
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken
import ru.vsu.cs.raspopov.authservice.security.SecurityProperties

class JwtAuthenticationConverter(
    private val securityProperties: SecurityProperties,

    private val tokenUtilStore: TokenUtilStore,
) : AuthenticationConverter {

    override fun convert(request: HttpServletRequest): Authentication? {
        val authorization = request.getHeader(securityProperties.headerString)
        if (authorization != null && authorization.startsWith(securityProperties.tokenPrefix)) {
            val token = authorization.substringAfter(securityProperties.tokenPrefix)
            val accessToken = tokenUtilStore.accessTokenStringDeserializer.invoke(token).getOrNull()
            accessToken?.let {
                return PreAuthenticatedAuthenticationToken(it, token)
            }

            val refreshToken = tokenUtilStore.refreshTokenStringDeserializer.invoke(token).getOrNull()
            refreshToken.let {
                return PreAuthenticatedAuthenticationToken(it, token)
            }
        }

        return null
    }
}