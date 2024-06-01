package ru.vsu.cs.raspopov.gatewayservice.filter

import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import ru.vsu.cs.raspopov.token.TokenValidationResponse
import ru.vsu.cs.raspopov.user.dto.UserDto
import ru.vsu.cs.raspopov.user.dto.request.AuthByNumberRequest

@Component
class AuthenticationFilter(
    private val webClientBuilder: WebClient.Builder,
) : AbstractGatewayFilterFactory<Any>() {

    override fun apply(config: Any): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            if (exchange.request.headers.containsKey(HttpHeaders.AUTHORIZATION).not()) {
                TODO()
            }

            val authHeader = exchange.request.headers[HttpHeaders.AUTHORIZATION]?.get(0)
                ?: TODO()

            val token = authHeader.substringAfter(AUTH_PREFIX)

            webClientBuilder.build()
                .post()
                .uri("lb://auth-service/api/token/validate")
                .headers { it.set(HttpHeaders.AUTHORIZATION, token) }
                .retrieve()
                .bodyToMono(TokenValidationResponse::class.java)
                .doOnError {
                    print(it)
                }
                .flatMap {
                    getUserByPhoneNumber(it.subject!!).flatMap { user ->
                        exchange.request
                            .mutate()
                            .headers { header ->
                                header.set(USER_ID_PREFIX, user.id.toString())
                            }

                        chain.filter(exchange)
                    }
                }
        }
    }

    private fun getUserByPhoneNumber(
        subject: String,
    ) = webClientBuilder.build()
        .post()
        .uri("lb://user-service/api/user/auth/by-number")
        .bodyValue(AuthByNumberRequest(subject))
        .retrieve()
        .bodyToMono(UserDto::class.java)

    class Config {

    }

    companion object {
        const val AUTH_PREFIX = "Bearer "
        const val USER_ID_PREFIX = "x-auth-user-id"
    }
}