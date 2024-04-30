package ru.vsu.cs.raspopov.authservice.sms.model.dto.request

data class ValidateOtpCodeRequest(
    val phone: String,
    val code: String,
)
