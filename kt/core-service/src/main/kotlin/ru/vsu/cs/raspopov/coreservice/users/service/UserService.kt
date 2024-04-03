package ru.vsu.cs.raspopov.coreservice.users.service

import ru.vsu.cs.raspopov.coreservice.users.model.dto.UpdatePasswordRequest
import ru.vsu.cs.raspopov.coreservice.users.model.dto.UserAuthRequest
import ru.vsu.cs.raspopov.coreservice.users.model.dto.UserDto
import ru.vsu.cs.raspopov.coreservice.users.model.enums.UserStatus

interface UserService {

    fun getUserById(id: Long): UserDto

    fun getUserByToken(token: String): UserDto

    fun createUser(userDto: UserDto): UserDto

    fun deleteUserById(id: Long)

    fun updateUserById(id: Long, userDto: UserDto): UserDto

    fun updateUserPasswordById(id: Long, request: UpdatePasswordRequest): UserDto

    fun changeUserStatusById(id: Long, status: UserStatus): UserDto

    fun authentication(request: UserAuthRequest): UserDto
}