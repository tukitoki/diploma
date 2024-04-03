package ru.vsu.cs.raspopov.authservice.service

import org.springframework.security.core.Authentication
import ru.vsu.cs.raspopov.authservice.model.dto.JwtTokens
import ru.vsu.cs.raspopov.authservice.model.dto.TokenParseResponse
import ru.vsu.cs.raspopov.authservice.model.dto.TokenValidationOutput
import ru.vsu.cs.raspopov.authservice.model.redis.AccessToken
import ru.vsu.cs.raspopov.authservice.model.redis.RefreshToken
import ru.vsu.cs.raspopov.authservice.model.redis.Token

interface ITokenService {

    fun serializeAccessToken(accessToken: AccessToken): String

    fun serializeRefreshToken(refreshToken: RefreshToken): String

    fun deserializeAccessToken(serializedAccessToken: String): AccessToken

    fun deserializeRefreshToken(serializedRefreshToken: String): RefreshToken

    fun validateSerializedAccessToken(serializedAccessToken: String): TokenValidationOutput

    fun createRefreshToken(auth: Authentication): RefreshToken

    fun createAccessToken(refreshToken: RefreshToken): AccessToken

    fun refreshToken(serializedRefreshToken: Token): JwtTokens

    fun parseToken(token: String): TokenParseResponse
}