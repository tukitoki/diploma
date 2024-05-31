package ru.vsu.cs.raspopov.coreservice.users.common.advice

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import ru.vsu.cs.raspopov.coreservice.users.common.exception.GeneralException

@RestControllerAdvice
class ExceptionAdvice {

    @ExceptionHandler(GeneralException::class)
    fun handleGeneralException(ex: GeneralException): ResponseEntity<String> {
        return ResponseEntity
            .status(ex.status)
            .body(ex.message)
    }
}