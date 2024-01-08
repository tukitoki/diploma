package ru.vsu.cs.raspopov.coreservice.users.api.docs

import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import ru.vsu.cs.raspopov.coreservice.users.model.dto.UpdatePasswordRequest
import ru.vsu.cs.raspopov.coreservice.users.model.dto.UserAuthRequest
import ru.vsu.cs.raspopov.coreservice.users.model.dto.UserDto
import ru.vsu.cs.raspopov.coreservice.users.model.enums.UserStatus

interface UserControllerAPI {

    @Operation(summary = "Get user by id")
    fun getUserById(id: Long): ResponseEntity<UserDto>

    @Operation(summary = "Create user")
    fun createUser(userDto: UserDto): ResponseEntity<UserDto>

    @Operation(summary = "Delete user by id")
    fun deleteUserById(id: Long): ResponseEntity<Unit>

    @Operation(summary = "Update user by id")
    fun updateUserById(id: Long, userDto: UserDto): ResponseEntity<UserDto>

    @Operation(summary = "Update user password")
    fun updateUserPasswordById(id: Long, request: UpdatePasswordRequest): ResponseEntity<UserDto>

    @Operation(summary = "Change user status")
    fun changeUserStatusById(id: Long, status: UserStatus): ResponseEntity<UserDto>

    @Operation(summary = "User authentication")
    fun authentication(request: UserAuthRequest): ResponseEntity<UserDto>
}