package ru.vsu.cs.raspopov.authservice.security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.intercept.AuthorizationFilter
import org.springframework.security.web.authentication.AuthenticationFilter
import ru.vsu.cs.raspopov.authservice.api.*
import ru.vsu.cs.raspopov.authservice.security.SecurityProperties

@EnableMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
class WebConfig(
    private val properties: SecurityProperties,

    private val authenticationManager: AuthenticationManager,
    private val tokenUtilStore: TokenUtilStore,
) {

    @Bean
    fun filterChain(
        http: HttpSecurity,
    ): SecurityFilterChain {
        return http
            .cors { }
            .csrf { it.disable() }
            .rememberMe { it.disable() }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authorizeHttpRequests {
                it.requestMatchers(LOGIN_PATH).permitAll()
                    .requestMatchers(REGISTER).permitAll()
                    .requestMatchers(AUTH_BY_OTP_PATH).permitAll()
                    .requestMatchers("/request-code/send").permitAll()
                    .requestMatchers(VALIDATE_ACCESS_TOKEN).permitAll()
                    .anyRequest().authenticated()
            }
            .addFilterAfter(
                AuthenticationFilter(
                    authenticationManager, JwtAuthenticationConverter(properties, tokenUtilStore)
                ), AuthorizationFilter::class.java
            )
            .build()
    }
}