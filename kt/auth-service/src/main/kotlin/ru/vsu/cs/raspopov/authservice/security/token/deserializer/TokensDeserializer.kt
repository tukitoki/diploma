package ru.vsu.cs.raspopov.authservice.security.token.deserializer

import com.nimbusds.jose.JWEDecrypter
import com.nimbusds.jose.JWSVerifier
import com.nimbusds.jwt.EncryptedJWT
import com.nimbusds.jwt.SignedJWT
import org.apache.logging.log4j.kotlin.Logging
import ru.vsu.cs.raspopov.authservice.tokens.model.entity.AccessToken
import ru.vsu.cs.raspopov.authservice.tokens.model.entity.RefreshToken
import java.util.*

class RefreshTokenStringDeserializer(
    private val jweDecrypter: JWEDecrypter,
) : AbstractTokenStringDeserializer<RefreshToken>() {

    override fun invoke(
        serializedRefreshToken: String
    ) = runCatching {
        val encryptedJWT = EncryptedJWT.parse(serializedRefreshToken)
        encryptedJWT.decrypt(this.jweDecrypter)

        val claims = encryptedJWT.jwtClaimsSet

        RefreshToken(
            claims.subject,
            claims.issueTime.toInstant(),
            claims.expirationTime.toInstant(),
            claims.getStringListClaim("authorities"),
            UUID.fromString(claims.jwtid),
        )
    }.onFailure {
        logger.error(it.localizedMessage, it)
    }

    private companion object : Logging
}

class AccessTokenStringDeserializer(
    private val jwsVerifier: JWSVerifier,
) : AbstractTokenStringDeserializer<AccessToken?>() {

    override fun invoke(
        serializedAccessToken: String,
    ) = runCatching {
        val signedJWT = SignedJWT.parse(serializedAccessToken)
        if (signedJWT.verify(this.jwsVerifier)) {
            val claims = signedJWT.jwtClaimsSet

            AccessToken(
                claims.subject,
                claims.issueTime.toInstant(),
                claims.expirationTime.toInstant(),
                claims.getStringListClaim("authorities"),
                UUID.fromString(claims.jwtid)
            )
        } else null
    }.onFailure {
        logger.error(it.localizedMessage, it)
    }

    private companion object : Logging
}