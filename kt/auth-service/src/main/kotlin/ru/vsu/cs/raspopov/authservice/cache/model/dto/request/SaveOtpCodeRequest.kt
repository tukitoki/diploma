package ru.vsu.cs.raspopov.authservice.cache.model.dto.request

import ru.vsu.cs.raspopov.authservice.cache.model.OtpCodeEntity
import java.util.UUID


data class SaveOtpCodeRequest(
    val id: UUID,
    val code: String,
    val ttl: Long = 90,
) {
    fun toEntity() = OtpCodeEntity(id, code, ttl)
}