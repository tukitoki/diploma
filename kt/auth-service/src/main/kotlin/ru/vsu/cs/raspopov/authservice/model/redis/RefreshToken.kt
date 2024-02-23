package ru.vsu.cs.raspopov.authservice.model.redis

import java.io.Serializable
import java.time.Instant
import java.util.UUID

class RefreshToken(
    subject: String,
    createdAt: Instant,
    expiredAt: Instant,
    authorities: List<String>,

    val jti: UUID,
): Token(subject, createdAt, expiredAt, authorities), Serializable