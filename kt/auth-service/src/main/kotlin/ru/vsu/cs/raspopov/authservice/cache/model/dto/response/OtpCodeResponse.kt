package ru.vsu.cs.raspopov.authservice.cache.model.dto.response

import ru.vsu.cs.raspopov.authservice.cache.model.OtpCodeEntity
import java.util.*

data class OtpCodeResponse(
    val id: UUID,
    val code: String,
) {
    constructor(
        optCodeEntity: OtpCodeEntity,
    ) : this(optCodeEntity.uuid, optCodeEntity.otpCode)
}