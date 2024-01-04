package ru.vsu.cs.raspopov.userservice.service

import ru.vsu.cs.raspopov.userservice.model.dto.UserDto

interface UserService {

    fun findById(id: Long): UserDto
}