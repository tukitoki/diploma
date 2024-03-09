package ru.vsu.cs.raspopov.authservice.exception

import org.springframework.http.HttpStatus

enum class ExceptionCode(
    val message: String,
    val status: HttpStatus,
) {

    TOKEN_EXPIRED("Token is expired", HttpStatus.UNAUTHORIZED),
    TOKEN_CANT_BE_PARSED("Given token can't be parsed", HttpStatus.BAD_REQUEST),
    TOKEN_CANT_BE_SERIALIZED("Given token can't be serialize", HttpStatus.INTERNAL_SERVER_ERROR),

}