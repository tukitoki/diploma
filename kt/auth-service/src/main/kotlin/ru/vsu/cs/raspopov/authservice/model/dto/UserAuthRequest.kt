package ru.vsu.cs.raspopov.authservice.model.dto

data class UserAuthRequest(
    val username: String,
    val password: String,
)
