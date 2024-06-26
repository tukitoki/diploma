package ru.vsu.cs.raspopov.authservice.tokens.service.impl

import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import ru.vsu.cs.raspopov.authservice.exception.ExceptionCode
import ru.vsu.cs.raspopov.authservice.exception.GeneralException
import ru.vsu.cs.raspopov.authservice.tokens.model.dto.JwtTokens
import ru.vsu.cs.raspopov.authservice.tokens.model.dto.response.TokenParseResponse
import ru.vsu.cs.raspopov.authservice.tokens.model.entity.AccessToken
import ru.vsu.cs.raspopov.authservice.tokens.model.entity.RefreshToken
import ru.vsu.cs.raspopov.authservice.tokens.model.entity.Token
import ru.vsu.cs.raspopov.authservice.security.config.TokenUtilStore
import ru.vsu.cs.raspopov.authservice.tokens.service.ITokenService
import ru.vsu.cs.raspopov.token.TokenValidationResponse

@Service
class TokenService(
    private val tokenUtilStore: TokenUtilStore,

) : ITokenService {

    override fun serializeAccessToken(accessToken: AccessToken): String {
        val token = tokenUtilStore.accessTokenStringSerializer.invoke(accessToken).getOrElse {
            throw GeneralException(ExceptionCode.TOKEN_CANT_BE_SERIALIZED)
        }

        return token
    }

    override fun serializeRefreshToken(refreshToken: RefreshToken): String {
        val token = tokenUtilStore.refreshTokenStringSerializer.invoke(refreshToken).getOrElse {
            throw GeneralException(ExceptionCode.TOKEN_CANT_BE_SERIALIZED)
        }

        return token
    }

    override fun deserializeAccessToken(serializedAccessToken: String): AccessToken {
        val token = tokenUtilStore.accessTokenStringDeserializer.invoke(serializedAccessToken).getOrElse {
            throw GeneralException(ExceptionCode.TOKEN_CANT_BE_PARSED)
        } ?: throw GeneralException("Token is invalid")

        return token
    }

    override fun deserializeRefreshToken(serializedRefreshToken: String): RefreshToken {
        val token = tokenUtilStore.refreshTokenStringDeserializer.invoke(serializedRefreshToken).getOrElse {
            throw GeneralException(ExceptionCode.TOKEN_CANT_BE_PARSED)
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

    override fun validateSerializedAccessToken(serializedAccessToken: String): TokenValidationResponse {
        val token = tokenUtilStore.accessTokenStringDeserializer.invoke(serializedAccessToken).getOrElse {
            throw GeneralException(ExceptionCode.TOKEN_CANT_BE_PARSED)
        } ?: throw GeneralException("Token is invalid")

        if (token.isExpired())
            throw GeneralException(ExceptionCode.TOKEN_EXPIRED)

        return TokenValidationResponse(true, token.subject)
    }

    override fun refreshToken(serializedRefreshToken: Token): JwtTokens {
        TODO("Not yet implemented")
    }

    override fun parseToken(token: String): TokenParseResponse {
        val deserializedToken = tokenUtilStore.accessTokenStringDeserializer.invoke(token).getOrElse {
            throw GeneralException(ExceptionCode.TOKEN_CANT_BE_PARSED)
        } ?: throw GeneralException("Token is invalid")

        return TokenParseResponse(deserializedToken.subject)
    }


}