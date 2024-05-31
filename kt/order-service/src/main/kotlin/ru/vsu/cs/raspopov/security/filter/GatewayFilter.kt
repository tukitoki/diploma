package ru.vsu.cs.raspopov.security.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.filter.OncePerRequestFilter

class GatewayFilter : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        // TODO: full implement
        val apiKey = request.getHeader(API_KEY_HEADER)
        val username = request.getHeader(USERNAME_HEADER)
        apiKey?.let {

        } ?: run {
            filterChain.doFilter(request, response)
            return
        }

        filterChain.doFilter(request, response)
    }

    private companion object {
        const val API_KEY_HEADER = "X-API-key"
        const val USERNAME_HEADER = "X-user-id"

        fun extractPayloadFromRequestHeaders(
            request: HttpServletRequest,
        ) {
            request.getHeader(API_KEY_HEADER)
            request.getHeader(USERNAME_HEADER)
        }

        data class GatewayPayload(
            val apiKey: String,
            val username: String,
        )
    }
}