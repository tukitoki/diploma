package ru.vsu.cs.raspopov.coreservice.users.model.dto

import ru.vsu.cs.raspopov.coreservice.users.model.entity.User
import ru.vsu.cs.raspopov.coreservice.users.model.enums.Authority
import ru.vsu.cs.raspopov.coreservice.users.model.enums.Role
import ru.vsu.cs.raspopov.coreservice.users.model.enums.UserStatus

data class UserDto(
    val userId: Long? = null,
    val username: String,
    val password: String,
    val email: String,
    val phone: String? = null,
    val status: UserStatus,
    val role: Role,
    val authorities: List<String>,
) {
    constructor(user: User) : this(
        userId = user.id.value,
        username = user.username,
        password = user.password,
        email = user.email,
        phone = user.phone,
        status = user.status,
        role = user.role,
        authorities = listOf(user.role.authority.toString())
    )
}