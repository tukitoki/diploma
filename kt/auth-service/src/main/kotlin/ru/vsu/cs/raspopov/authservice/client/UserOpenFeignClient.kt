package ru.vsu.cs.raspopov.authservice.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import ru.vsu.cs.raspopov.authservice.model.dto.request.UserAuthRequest
import ru.vsu.cs.raspopov.authservice.model.dto.UserDto

@FeignClient(name = "user-service")
interface UserOpenFeignClient {

    @RequestMapping(method = [RequestMethod.POST], value = ["api/user/auth"])
    fun authenticateUser(request: UserAuthRequest): UserDto
}