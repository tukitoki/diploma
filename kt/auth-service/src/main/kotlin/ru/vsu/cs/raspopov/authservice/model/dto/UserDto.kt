package ru.vsu.cs.raspopov.authservice.model.dto

import ru.vsu.cs.raspopov.authservice.model.enums.Role
import ru.vsu.cs.raspopov.authservice.model.enums.UserStatus

data class UserDto(
    val userId: Long? = null,
    val username: String,
    val password: String,
    val email: String,
    val phone: String? = null,
    val status: UserStatus,
    val role: Role,
    val authorities: List<String>,
)