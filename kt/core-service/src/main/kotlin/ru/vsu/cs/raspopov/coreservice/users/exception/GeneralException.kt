package ru.vsu.cs.raspopov.coreservice.users.exception

import org.springframework.http.HttpStatus

class GeneralException(
    message: String,
    var status: HttpStatus = HttpStatus.BAD_REQUEST
) : RuntimeException(message)