package ru.vsu.cs.raspopov.userservice.model.dto

data class UserDto(
    val userId: Long,
    val username: String,
    val password: String,
    val email: String,
    val phone: String,
)