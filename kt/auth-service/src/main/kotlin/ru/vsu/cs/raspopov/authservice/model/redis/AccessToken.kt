package ru.vsu.cs.raspopov.authservice.model.redis

import java.io.Serializable
import java.time.Instant
import java.util.UUID

class AccessToken(
    subject: String,
    createdAt: Instant,
    expiredAt: Instant,
    authorities: List<String>,

    val refreshTokenJti: UUID,
): Token(subject, createdAt, expiredAt, authorities), Serializable