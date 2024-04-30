package ru.vsu.cs.raspopov.authservice.sms.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.vsu.cs.raspopov.authservice.sms.model.dto.request.SendOtpCodeRequest
import ru.vsu.cs.raspopov.authservice.sms.service.IRequestCodeService

@RequestMapping("/request-code")
@RestController
class RequestCodeController(
    private val requestCodeService: IRequestCodeService
) {

    @PostMapping("/send")
    fun sendRequestCode(
        @RequestBody request: SendOtpCodeRequest,
    ): ResponseEntity<Unit> {
        requestCodeService.sendRequestCode(request)

        return ResponseEntity.ok().build()
    }
}