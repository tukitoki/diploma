package ru.vsu.cs.raspopov.userservice.model.dto

data class UpdatePasswordRequest(
    val userId: Long,
    val password: String,
)