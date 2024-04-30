package ru.vsu.cs.raspopov.authservice.tokens.service

import org.springframework.security.core.Authentication
import ru.vsu.cs.raspopov.authservice.tokens.model.dto.JwtTokens
import ru.vsu.cs.raspopov.authservice.tokens.model.dto.response.TokenParseResponse
import ru.vsu.cs.raspopov.authservice.tokens.model.dto.response.TokenValidationResponse
import ru.vsu.cs.raspopov.authservice.tokens.model.entity.AccessToken
import ru.vsu.cs.raspopov.authservice.tokens.model.entity.RefreshToken
import ru.vsu.cs.raspopov.authservice.tokens.model.entity.Token

interface ITokenService {

    fun serializeAccessToken(accessToken: AccessToken): String

    fun serializeRefreshToken(refreshToken: RefreshToken): String

    fun deserializeAccessToken(serializedAccessToken: String): AccessToken

    fun deserializeRefreshToken(serializedRefreshToken: String): RefreshToken

    fun validateSerializedAccessToken(serializedAccessToken: String): TokenValidationResponse

    fun createRefreshToken(auth: Authentication): RefreshToken

    fun createAccessToken(refreshToken: RefreshToken): AccessToken

    fun refreshToken(serializedRefreshToken: Token): JwtTokens

    fun parseToken(token: String): TokenParseResponse
}