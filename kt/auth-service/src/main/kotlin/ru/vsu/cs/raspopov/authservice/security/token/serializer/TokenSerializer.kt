package ru.vsu.cs.raspopov.authservice.security.token.serializer

import com.nimbusds.jose.*
import com.nimbusds.jose.crypto.DirectEncrypter
import com.nimbusds.jose.crypto.MACSigner
import com.nimbusds.jwt.EncryptedJWT
import com.nimbusds.jwt.JWTClaimsSet
import com.nimbusds.jwt.SignedJWT
import org.apache.logging.log4j.kotlin.Logging
import ru.vsu.cs.raspopov.authservice.tokens.model.entity.AccessToken
import ru.vsu.cs.raspopov.authservice.tokens.model.entity.RefreshToken
import java.util.*

class JweRefreshTokenStringSerializer(
    private val jweEncrypter: DirectEncrypter,
    private val jweAlgorithm: JWEAlgorithm = JWEAlgorithm.DIR,
    private val encryptionMethod: EncryptionMethod = EncryptionMethod.A128GCM,
) : AbstractTokenStringSerializer<RefreshToken>() {

    override fun invoke(token: RefreshToken): Result<String> {
        val header = JWEHeader.Builder(jweAlgorithm, encryptionMethod)
            .keyID(token.jti.toString())
            .build()

        val claims = JWTClaimsSet.Builder()
            .jwtID(token.jti.toString())
            .subject(token.subject)
            .issueTime(Date.from(token.createdAt))
            .expirationTime(Date.from(token.expiredAt))
            .claim("authorities", token.authorities)
            .build()

        val encryptedJWT = EncryptedJWT(header, claims)

        return runCatching {
            encryptedJWT.encrypt(jweEncrypter)

            encryptedJWT.serialize()
        }.onFailure {
            logger.error("error")
        }
    }

    private companion object : Logging
}

class JwsAccessTokenStringSerializer(
    private val jwsSigner: MACSigner,
    private val jwsAlgorithm: JWSAlgorithm = JWSAlgorithm.HS256,
) : AbstractTokenStringSerializer<AccessToken>() {

    override fun invoke(token: AccessToken): Result<String> {
        val header = JWSHeader.Builder(this.jwsAlgorithm)
            .keyID(token.refreshTokenJti.toString())
            .build()

        val claims = JWTClaimsSet.Builder()
            .jwtID(token.refreshTokenJti.toString())
            .subject(token.subject)
            .issueTime(Date.from(token.createdAt))
            .expirationTime(Date.from(token.expiredAt))
            .claim("authorities", token.authorities)
            .build()

        val signedJWT = SignedJWT(header, claims)

        return runCatching {
            signedJWT.sign(jwsSigner)

            signedJWT.serialize()
        }.onFailure {
            logger.error("error", it)
        }
    }

    private companion object : Logging
}