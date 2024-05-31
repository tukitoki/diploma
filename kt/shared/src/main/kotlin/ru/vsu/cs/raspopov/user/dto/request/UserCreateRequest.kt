package ru.vsu.cs.raspopov.user.dto.request

import ru.vsu.cs.raspopov.user.enums.UserStatus

data class UserCreateRequest(
    val username: String?,
    val password: String?,
    val email: String?,
    val phone: String?,
    val status: UserStatus,
    val role: String,
    val authorities: List<String>,
)