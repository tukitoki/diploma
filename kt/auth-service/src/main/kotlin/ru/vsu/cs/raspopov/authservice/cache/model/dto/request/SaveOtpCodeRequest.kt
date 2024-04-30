package ru.vsu.cs.raspopov.authservice.cache.model.dto.request

import ru.vsu.cs.raspopov.authservice.cache.model.OtpCodeEntity

data class SaveOtpCodeRequest(
    val id: String,
    val code: String,
    val ttl: Long = 90,
) {
    fun toEntity() = OtpCodeEntity(id, code, ttl)
}