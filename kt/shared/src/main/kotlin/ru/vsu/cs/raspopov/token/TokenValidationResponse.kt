package ru.vsu.cs.raspopov.token

data class TokenValidationResponse(
    val isValid: Boolean,
    val subject: String?,
)
