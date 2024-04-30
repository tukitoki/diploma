package ru.vsu.cs.raspopov.authservice.security.authManager

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import ru.vsu.cs.raspopov.authservice.client.user.UserOpenFeignClient
import ru.vsu.cs.raspopov.authservice.client.user.dto.AuthByNumberRequest

@Component
class OtpCodeAuthenticationManager(
    private val userOpenFeignClient: UserOpenFeignClient,
) : AuthenticationManager {

    override fun authenticate(authentication: Authentication): Authentication {
//        val phoneNumber = (authentication.principal as String)

        val user = userOpenFeignClient.authenticateUserByNumber(
            AuthByNumberRequest(authentication.name)
        )

        return UsernamePasswordAuthenticationToken(
            user.username,
            user.password,
            user.authorities.map { SimpleGrantedAuthority(it) }
        )
    }
}