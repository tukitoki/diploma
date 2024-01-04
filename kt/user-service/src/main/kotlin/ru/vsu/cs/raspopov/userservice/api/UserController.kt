package ru.vsu.cs.raspopov.userservice.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.vsu.cs.raspopov.userservice.api.docs.UserControllerAPI
import ru.vsu.cs.raspopov.userservice.model.dto.UpdatePasswordRequest
import ru.vsu.cs.raspopov.userservice.model.dto.UserDto

@RequestMapping("/user")
@RestController
class UserController(

) : UserControllerAPI {

    @GetMapping("/{id}")
    override fun getUserById(@PathVariable id: Long): ResponseEntity<UserDto> {
        TODO("Not yet implemented")
    }

    @PostMapping
    override fun createUser(@RequestBody userDto: UserDto): ResponseEntity<UserDto> {
        TODO("Not yet implemented")
    }

    @DeleteMapping("/{id}")
    override fun deleteUserById(@PathVariable id: Long): ResponseEntity<*> {
        TODO("Not yet implemented")
    }

    @PutMapping("/{id}")
    override fun updateUserById(
        @PathVariable id: Long,
        @RequestBody userDto: UserDto,
    ): ResponseEntity<UserDto> {
        TODO("Not yet implemented")
    }

    @PatchMapping("/{id}/password-update")
    override fun updateUserPasswordById(
        @PathVariable id: Long,
        @RequestBody request: UpdatePasswordRequest,
    ): ResponseEntity<UserDto> {
        TODO("Not yet implemented")
    }
}