package ru.vsu.cs.raspopov.authservice.model.dto

data class JwtTokens(
    val accessToken: String,
//    val accessTokenExpiry: String,
    val refreshToken: String,
//    val refreshTokenExpiry: String,
)