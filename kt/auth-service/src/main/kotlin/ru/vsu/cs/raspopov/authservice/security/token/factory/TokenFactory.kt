package ru.vsu.cs.raspopov.authservice.security.token.factory

import org.springframework.security.core.Authentication
import ru.vsu.cs.raspopov.authservice.tokens.model.entity.AccessToken
import ru.vsu.cs.raspopov.authservice.tokens.model.entity.RefreshToken
import java.time.Duration
import java.time.Instant
import java.util.UUID

class AccessTokenFactory(
    accessTokenTtl: Long,
): AbstractTokenFactory<RefreshToken, AccessToken>(accessTokenTtl) {

    private val accessTokenTtl = Duration.ofSeconds(accessTokenTtl)

    override fun invoke(refreshToken: RefreshToken): AccessToken {
        val now = Instant.now()
        val authorities = refreshToken.authorities
            .map { it.substringAfter("GRANT_", "") }
            .filter { it.isNotEmpty() }

        return AccessToken(
            refreshToken.subject,
            now,
            now.plus(accessTokenTtl),
            authorities,
            refreshToken.jti,
        )
    }
}

class RefreshTokenFactory(
    refreshTokenTtl: Long,
): AbstractTokenFactory<Authentication, RefreshToken>(refreshTokenTtl) {

    private val accessTokenTtl = Duration.ofSeconds(refreshTokenTtl)

    override fun invoke(authentication: Authentication): RefreshToken {
        val now = Instant.now()
        val authorities = listOf(
            "JWT_REFRESH",
            "JWT_LOGOUT",
        ) + authentication.authorities.map {
            "GRANT_" + it.authority
        }

        return RefreshToken(
            authentication.name,
            now,
            now.plus(accessTokenTtl),
            authorities,
            UUID.randomUUID(),
        )
    }
}