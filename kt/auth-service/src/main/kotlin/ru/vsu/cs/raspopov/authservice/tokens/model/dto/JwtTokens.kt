package ru.vsu.cs.raspopov.authservice.tokens.model.dto

data class JwtTokens(
    val accessToken: String,
//    val accessTokenExpiry: String,
    val refreshToken: String,
//    val refreshTokenExpiry: String,
)