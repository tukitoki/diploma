package ru.vsu.cs.raspopov.authservice.tokens.model.entity

import java.time.Duration
import java.time.Instant

abstract class Token(
    val subject: String,
    val createdAt: Instant,
    val expiredAt: Instant,
    val authorities: List<String>,
) {

    val ttl: Long = Duration.between(createdAt, expiredAt).seconds

    fun isExpired() = this.expiredAt.isBefore(Instant.now())
}