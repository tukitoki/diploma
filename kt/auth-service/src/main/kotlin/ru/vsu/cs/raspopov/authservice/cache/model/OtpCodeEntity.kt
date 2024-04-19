package ru.vsu.cs.raspopov.authservice.cache.model

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import java.util.*

@RedisHash(value = "otp-code")
class OtpCodeEntity(
    @Id
    val uuid: UUID,

    val otpCode: String,

    @TimeToLive
    val ttl: Long = 90,
)