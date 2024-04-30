package ru.vsu.cs.raspopov.authservice.sms.service

import ru.vsu.cs.raspopov.authservice.sms.model.dto.request.SendOtpCodeRequest
import ru.vsu.cs.raspopov.authservice.sms.model.dto.request.ValidateOtpCodeRequest

interface IRequestCodeService {

    fun sendRequestCode(request: SendOtpCodeRequest)

    fun validateRequestCode(request: ValidateOtpCodeRequest)
}