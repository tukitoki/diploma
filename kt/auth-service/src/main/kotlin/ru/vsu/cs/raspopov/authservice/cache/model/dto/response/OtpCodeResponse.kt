package ru.vsu.cs.raspopov.authservice.cache.model.dto.response

import ru.vsu.cs.raspopov.authservice.cache.model.OtpCodeEntity

data class OtpCodeResponse(
    val id: String,
    val code: String,
) {
    constructor(
        optCodeEntity: OtpCodeEntity,
    ) : this(optCodeEntity.id, optCodeEntity.otpCode)
}