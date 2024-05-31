package ru.vsu.cs.raspopov.coreservice.users.common.exception

import org.springframework.http.HttpStatus

enum class ExceptionCode(
    val message: String,
    val status: HttpStatus,
) {

    AUTH_FAILED("Invalid username or password", HttpStatus.BAD_REQUEST),
    AUTH_BY_NUMBER_FAILED("Incorrect phone", HttpStatus.BAD_REQUEST),
    USER_EXITS_BY_ID("User by phone already exists", HttpStatus.CONFLICT)
}