package ru.vsu.cs.raspopov.security.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import ru.vsu.cs.raspopov.client.customer.CustomerOpenFeignClient
import ru.vsu.cs.raspopov.client.customer.dto.CustomerByUserRequest

class GatewayFilter(
    private val customerClient: CustomerOpenFeignClient,
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val userId = request.getHeader(USER_ID_HEADER).toLong()
        val customerDto = customerClient.getCustomerByUserId(CustomerByUserRequest(userId))

        val auth = UsernamePasswordAuthenticationToken(
            customerDto,
            null,
            // TODO: impl
            listOf(SimpleGrantedAuthority("ROLE_USER")),
        )

        SecurityContextHolder.getContext().authentication = auth
        filterChain.doFilter(request, response)
    }

    private companion object {
        const val API_KEY_HEADER = "X-API-key"
        const val USER_ID_HEADER = "x-auth-user-id"

        fun extractPayloadFromRequestHeaders(
            request: HttpServletRequest,
        ) {
//            request.getHeader(API_KEY_HEADER)
            request.getHeader(USER_ID_HEADER)
        }

        data class GatewayPayload(
//            val apiKey: String,
            val id: Long,
        )
    }
}