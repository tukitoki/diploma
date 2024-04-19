package ru.vsu.cs.raspopov.authservice.model.dto.request

data class UserAuthRequest(
    val username: String,
    val password: String,
)
