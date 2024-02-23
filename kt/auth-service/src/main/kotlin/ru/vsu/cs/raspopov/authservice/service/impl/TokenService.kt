package ru.vsu.cs.raspopov.authservice.service.impl

import org.springframework.security.authorization.AuthorizationManager
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import ru.vsu.cs.raspopov.authservice.model.dto.JwtTokens
import ru.vsu.cs.raspopov.authservice.model.dto.TokenValidationOutput
import ru.vsu.cs.raspopov.authservice.model.redis.AccessToken
import ru.vsu.cs.raspopov.authservice.model.redis.RefreshToken
import ru.vsu.cs.raspopov.authservice.model.redis.Token
import ru.vsu.cs.raspopov.authservice.security.config.TokenUtilStore
import ru.vsu.cs.raspopov.authservice.service.ITokenService
import java.time.Instant
import java.time.LocalDate

@Service
class TokenService(
    private val tokenUtilStore: TokenUtilStore,

) : ITokenService {

    override fun serializeAccessToken(accessToken: AccessToken): String {
        val token = tokenUtilStore.accessTokenStringSerializer.invoke(accessToken).getOrElse {
            TODO("MAKE CORRECT THROWABLE")
        }

        return token
    }

    override fun serializeRefreshToken(refreshToken: RefreshToken): String {
        val token = tokenUtilStore.refreshTokenStringSerializer.invoke(refreshToken).getOrElse {
            TODO("MAKE CORRECT THROWABLE")
        }

        return token
    }

    override fun deserializeAccessToken(serializedAccessToken: String): AccessToken {
        val token = tokenUtilStore.accessTokenStringDeserializer.invoke(serializedAccessToken).getOrElse {
            TODO("MAKE CORRECT THROWABLE")
        } ?: TODO("MAKE CORRECT THROWABLE WITH NULLABLE TOKEN")

        return token
    }

    override fun deserializeRefreshToken(serializedRefreshToken: String): RefreshToken {
        val token = tokenUtilStore.refreshTokenStringDeserializer.invoke(serializedRefreshToken).getOrElse {
            TODO("MAKE CORRECT THROWABLE")
        }

        return token
    }

    override fun createRefreshToken(auth: Authentication): RefreshToken {
        val token = tokenUtilStore.refreshTokenFactory.invoke(auth)

        return token
    }

    override fun createAccessToken(refreshToken: RefreshToken): AccessToken {
        val token = tokenUtilStore.accessTokenFactory.invoke(refreshToken)

        return token
    }

    override fun validateSerializedAccessToken(serializedAccessToken: String): TokenValidationOutput {
        val token = tokenUtilStore.refreshTokenStringDeserializer.invoke(serializedAccessToken).getOrElse {
            TODO()
        }

        if (token.expiredAt.isBefore(Instant.now())) {
            TODO()
        }

        return TokenValidationOutput(true)
    }

    override fun refreshToken(serializedRefreshToken: Token): JwtTokens {
        TODO("Not yet implemented")
    }


}