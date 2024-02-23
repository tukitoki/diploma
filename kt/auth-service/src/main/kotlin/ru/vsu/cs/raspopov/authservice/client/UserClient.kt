package ru.vsu.cs.raspopov.authservice.client

import org.apache.logging.log4j.kotlin.Logging
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import ru.vsu.cs.raspopov.authservice.model.dto.UserAuthRequest
import ru.vsu.cs.raspopov.authservice.model.dto.UserDto

@Component
class UserClient {

    private val restClient = RestClient.builder()
        .baseUrl("http://localhost:9100/api/user/auth")
        .build()

    fun authenticateUser(request: UserAuthRequest): Result<UserDto?> {
        val headers = HttpHeaders().apply {
            this.contentType = MediaType.APPLICATION_JSON
        }

        return runCatching {
            restClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .toEntity(UserDto::class.java)
                .body
        }.onFailure {
            logger.error("Error when try to get user from user service", it)
        }
    }

    private companion object : Logging
}