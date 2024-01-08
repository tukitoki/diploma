package ru.vsu.cs.raspopov.coreservice.users.model.dto

data class UserAuthRequest(
    val username: String,
    val password: String,
)
