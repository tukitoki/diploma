package ru.vsu.cs.raspopov.common.exception

import org.springframework.http.HttpStatus

enum class ExceptionCode(
    val message: String,
    val status: HttpStatus,
) {

    ORDER_NOT_FOUND_BY_ID("Not found order by id", HttpStatus.NOT_FOUND),
    TEMPORARY_ORDER_NO_CONTENT("No content", HttpStatus.NO_CONTENT),
}