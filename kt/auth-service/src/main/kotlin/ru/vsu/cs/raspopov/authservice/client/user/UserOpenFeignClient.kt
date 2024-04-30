package ru.vsu.cs.raspopov.authservice.client.user

import jakarta.validation.Valid
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import ru.vsu.cs.raspopov.authservice.authenticate.dto.request.AuthRequest
import ru.vsu.cs.raspopov.authservice.client.user.dto.AuthByNumberRequest
import ru.vsu.cs.raspopov.authservice.client.user.dto.UserDto

@FeignClient(name = "user-service")
interface UserOpenFeignClient {

    @RequestMapping(method = [RequestMethod.POST], value = ["/api/user/auth"])
    fun authenticateUser(request: AuthRequest): UserDto

    @RequestMapping(method = [RequestMethod.POST], value = ["/api/user/auth-by-number"])
    fun authenticateUserByNumber(request: AuthByNumberRequest): UserDto
}