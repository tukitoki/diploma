package ru.vsu.cs.raspopov.coreservice.users.model.dto

data class UpdatePasswordRequest(
    val userId: Long,
    val password: String,
)