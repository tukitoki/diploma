package ru.vsu.cs.raspopov.coreservice.users.api

import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.*
import ru.vsu.cs.raspopov.coreservice.users.api.docs.UserControllerAPI
import ru.vsu.cs.raspopov.coreservice.users.model.dto.AuthByNumberRequest
import ru.vsu.cs.raspopov.coreservice.users.model.dto.UpdatePasswordRequest
import ru.vsu.cs.raspopov.coreservice.users.model.dto.UserAuthRequest
import ru.vsu.cs.raspopov.coreservice.users.model.dto.UserDto
import ru.vsu.cs.raspopov.coreservice.users.model.enums.UserStatus
import ru.vsu.cs.raspopov.coreservice.users.service.UserService

@RequestMapping("/api/user")
@RestController
class UserController(
    private val userService: UserService,
) : UserControllerAPI {

    @GetMapping("/{id}")
    override fun getUserById(@PathVariable id: Long): ResponseEntity<UserDto> {

        return ok(userService.getUserById(id))
    }

    @GetMapping("/by-token")
    override fun getUserByToken(
        @RequestHeader(HttpHeaders.AUTHORIZATION) token: String,
    ): ResponseEntity<UserDto> {

        return ok(userService.getUserByToken(token))
    }

    @PostMapping
    override fun createUser(
        @RequestBody userDto: UserDto
    ): ResponseEntity<UserDto> {

        return ok(userService.createUser(userDto))
    }

    @DeleteMapping("/{id}")
    override fun deleteUserById(@PathVariable id: Long): ResponseEntity<Unit> {

        userService.deleteUserById(id)

        return ok().build()
    }

    @PutMapping("/{id}")
    override fun updateUserById(
        @PathVariable id: Long,
        @RequestBody userDto: UserDto,
    ): ResponseEntity<UserDto> {

        return ok(userService.updateUserById(id, userDto))
    }

    @PatchMapping("/{id}/password-update")
    override fun updateUserPasswordById(
        @PathVariable id: Long,
        @RequestBody request: UpdatePasswordRequest,
    ): ResponseEntity<UserDto> {

        return ok(userService.updateUserPasswordById(id, request))
    }

    @PutMapping("/{id}/status-change")
    override fun changeUserStatusById(
        @PathVariable id: Long,
        @RequestParam status: UserStatus,
    ): ResponseEntity<UserDto> {

        return ok(userService.changeUserStatusById(id, status))
    }

    @PostMapping("/auth")
    override fun authentication(
        @RequestBody request: UserAuthRequest,
    ): ResponseEntity<UserDto> {

        return ok(userService.authentication(request))
    }

    @PostMapping("/auth-by-number")
    override fun authenticationByNumber(
        @RequestBody request: AuthByNumberRequest,
    ): ResponseEntity<UserDto> {

        return ok(userService.authenticationByNumber(request))
    }
}