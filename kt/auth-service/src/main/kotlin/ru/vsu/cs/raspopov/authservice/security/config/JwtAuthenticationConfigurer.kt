package ru.vsu.cs.raspopov.authservice.security.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer
import org.springframework.security.web.authentication.AuthenticationFilter
import ru.vsu.cs.raspopov.authservice.model.redis.AccessToken
import ru.vsu.cs.raspopov.authservice.model.redis.RefreshToken
import ru.vsu.cs.raspopov.authservice.model.redis.Token
import ru.vsu.cs.raspopov.authservice.security.SecurityProperties
import ru.vsu.cs.raspopov.authservice.security.filter.RequestJwtTokensFilter
import ru.vsu.cs.raspopov.authservice.security.token.deserializer.AbstractTokenStringDeserializer
import ru.vsu.cs.raspopov.authservice.security.token.serializer.AbstractTokenStringSerializer

class JwtAuthenticationConfigurer(
    private val refreshTokenStringSerializer: AbstractTokenStringSerializer<Token>,
    private val accessTokenStringSerializer: AbstractTokenStringSerializer<Token>,

    private val refreshTokenStringDeserializer: AbstractTokenStringDeserializer<AccessToken?>,
    private val accessTokenStringDeserializer: AbstractTokenStringDeserializer<RefreshToken>,

    private val properties: SecurityProperties,
    private val objectMapper: ObjectMapper,
) : AbstractHttpConfigurer<JwtAuthenticationConfigurer, HttpSecurity>() {

    override fun init(builder: HttpSecurity?) {
        super.init(builder)
    }

    override fun configure(builder: HttpSecurity) {
        val reqJwtTokensFilter = RequestJwtTokensFilter(
            refreshTokenStringSerializer = refreshTokenStringSerializer,
            accessTokenStringSerializer = accessTokenStringSerializer,
            objectMapper = objectMapper,
            properties = properties
        )

//        val jwtAuthenticationFilter = AuthenticationFilter(
//            builder.getSharedObject(AuthenticationManager::class.java),
//            JwtAuthenticationConverter(properties, refreshTokenStringDeserializer, accessTokenStringDeserializer)
//        )

    }
}