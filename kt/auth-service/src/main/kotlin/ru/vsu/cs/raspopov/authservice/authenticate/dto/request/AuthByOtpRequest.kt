package ru.vsu.cs.raspopov.authservice.authenticate.dto.request

data class AuthByOtpRequest(
    val phoneNumber: String,
    val code: String
)