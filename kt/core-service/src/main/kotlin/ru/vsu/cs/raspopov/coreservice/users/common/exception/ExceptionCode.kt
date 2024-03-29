package ru.vsu.cs.raspopov.coreservice.users.common.exception

import org.springframework.http.HttpStatus

enum class ExceptionCode(
    val message: String,
    val status: HttpStatus,
) {

    AUTH_FAILED("Invalid username or password", HttpStatus.BAD_REQUEST)
}