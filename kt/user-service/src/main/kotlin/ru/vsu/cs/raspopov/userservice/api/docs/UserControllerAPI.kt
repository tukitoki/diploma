package ru.vsu.cs.raspopov.userservice.api.docs

import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import ru.vsu.cs.raspopov.userservice.model.dto.UpdatePasswordRequest
import ru.vsu.cs.raspopov.userservice.model.dto.UserDto

interface UserControllerAPI {

    @Operation(summary = "Get user by id")
    fun getUserById(id: Long): ResponseEntity<UserDto>

    @Operation(summary = "Create user")
    fun createUser(userDto: UserDto): ResponseEntity<UserDto>

    @Operation(summary = "Delete user by id")
    fun deleteUserById(id: Long): ResponseEntity<*>

    @Operation(summary = "Update user by id")
    fun updateUserById(id: Long, userDto: UserDto): ResponseEntity<UserDto>

    @Operation(summary = "Update user password")
    fun updateUserPasswordById(id: Long, request: UpdatePasswordRequest): ResponseEntity<UserDto>
}