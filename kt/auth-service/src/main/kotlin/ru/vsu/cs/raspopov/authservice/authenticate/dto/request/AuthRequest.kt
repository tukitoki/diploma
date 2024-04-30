package ru.vsu.cs.raspopov.authservice.authenticate.dto.request

data class AuthRequest(
    val username: String,
    val password: String,
)
