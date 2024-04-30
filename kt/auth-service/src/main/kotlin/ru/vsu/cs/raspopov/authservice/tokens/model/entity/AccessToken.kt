package ru.vsu.cs.raspopov.authservice.tokens.model.entity

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