package ru.vsu.cs.raspopov.authservice.security.authManager

import org.springframework.context.annotation.Primary
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import ru.vsu.cs.raspopov.authservice.client.user.UserOpenFeignClient
import ru.vsu.cs.raspopov.authservice.authenticate.dto.request.AuthRequest
import ru.vsu.cs.raspopov.authservice.tokens.model.entity.Token
import java.time.Instant

@Primary
@Component
class AppAuthenticationManager(
    private val userOpenFeignClient: UserOpenFeignClient,
) : AuthenticationManager {

    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication): Authentication {
        val principal = authentication.principal
        if (principal is Token) {
            if (principal.expiredAt.isBefore(Instant.now()))
                throw RuntimeException("Token is expired")

            return UsernamePasswordAuthenticationToken(
                principal.subject,
                null,
                principal.authorities.map { SimpleGrantedAuthority(it) }
            )
        }

        val password = authentication.credentials.toString()
        val user = userOpenFeignClient.authenticateUser(
            AuthRequest(
                authentication.name,
                password,
            )
        )

        return UsernamePasswordAuthenticationToken(
            user.username,
            user.password,
            user.authorities.map { SimpleGrantedAuthority(it) }
        )
    }
}