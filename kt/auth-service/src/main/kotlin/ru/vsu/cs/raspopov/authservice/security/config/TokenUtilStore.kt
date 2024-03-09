package ru.vsu.cs.raspopov.authservice.security.config

import com.nimbusds.jose.crypto.DirectDecrypter
import com.nimbusds.jose.crypto.DirectEncrypter
import com.nimbusds.jose.crypto.MACSigner
import com.nimbusds.jose.crypto.MACVerifier
import com.nimbusds.jose.jwk.OctetSequenceKey
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import ru.vsu.cs.raspopov.authservice.model.redis.AccessToken
import ru.vsu.cs.raspopov.authservice.model.redis.RefreshToken
import ru.vsu.cs.raspopov.authservice.security.SecurityProperties
import ru.vsu.cs.raspopov.authservice.security.token.deserializer.AbstractTokenStringDeserializer
import ru.vsu.cs.raspopov.authservice.security.token.deserializer.AccessTokenStringDeserializer
import ru.vsu.cs.raspopov.authservice.security.token.deserializer.RefreshTokenStringDeserializer
import ru.vsu.cs.raspopov.authservice.security.token.factory.AbstractTokenFactory
import ru.vsu.cs.raspopov.authservice.security.token.factory.AccessTokenFactory
import ru.vsu.cs.raspopov.authservice.security.token.factory.RefreshTokenFactory
import ru.vsu.cs.raspopov.authservice.security.token.serializer.AbstractTokenStringSerializer
import ru.vsu.cs.raspopov.authservice.security.token.serializer.JweRefreshTokenStringSerializer
import ru.vsu.cs.raspopov.authservice.security.token.serializer.JwsAccessTokenStringSerializer

@Component
class TokenUtilStore(
    private val properties: SecurityProperties,
) {
    
    final val refreshTokenStringDeserializer: AbstractTokenStringDeserializer<RefreshToken>
    final val accessTokenStringDeserializer: AbstractTokenStringDeserializer<AccessToken?>
    
    final val refreshTokenStringSerializer: AbstractTokenStringSerializer<RefreshToken>
    final val accessTokenStringSerializer: AbstractTokenStringSerializer<AccessToken>
    
    final val refreshTokenFactory: AbstractTokenFactory<Authentication, RefreshToken>
    final val accessTokenFactory: AbstractTokenFactory<RefreshToken, AccessToken>

    init {
        refreshTokenStringDeserializer = RefreshTokenStringDeserializer(
            DirectDecrypter(OctetSequenceKey.parse(properties.refreshSecretKey))
        )
        accessTokenStringDeserializer = AccessTokenStringDeserializer(
            MACVerifier(properties.accessSecretKey)
        )

        refreshTokenStringSerializer = JweRefreshTokenStringSerializer(
            DirectEncrypter(OctetSequenceKey.parse(properties.refreshSecretKey)),
        )
        accessTokenStringSerializer = JwsAccessTokenStringSerializer(
            MACSigner(properties.accessSecretKey)
        )

        refreshTokenFactory = RefreshTokenFactory(properties.refreshTknTtl)
        accessTokenFactory = AccessTokenFactory(properties.accessTknTtl)
    }
    
}