package ru.vsu.cs.raspopov.authservice.security

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import ru.vsu.cs.raspopov.authservice.client.UserClient
import ru.vsu.cs.raspopov.authservice.model.dto.UserAuthRequest
import ru.vsu.cs.raspopov.authservice.model.redis.AccessToken
import ru.vsu.cs.raspopov.authservice.model.redis.Token
import java.time.Instant

@Component
class AppAuthenticationManager(
    private val userClient: UserClient,
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
        val user = userClient.authenticateUser(
            UserAuthRequest(
                authentication.name,
                password,
            )
        ).getOrElse {
            TODO("GOOD THROWABLE")
        } ?: TODO()

        return UsernamePasswordAuthenticationToken(
            user.username,
            user.password,
            user.authorities.map { SimpleGrantedAuthority(it) }
        )
    }
}