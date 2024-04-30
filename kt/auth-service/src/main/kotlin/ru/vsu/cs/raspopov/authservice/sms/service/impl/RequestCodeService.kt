package ru.vsu.cs.raspopov.authservice.sms.service.impl

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Service
import ru.vsu.cs.raspopov.authservice.sms.model.dto.request.SendOtpCodeRequest
import ru.vsu.cs.raspopov.authservice.sms.model.dto.request.ValidateOtpCodeRequest
import ru.vsu.cs.raspopov.authservice.sms.service.IRequestCodeService

@ConditionalOnProperty(name = ["service.mock"], havingValue = "false")
@Service
class RequestCodeService : IRequestCodeService {

    override fun sendRequestCode(request: SendOtpCodeRequest) {
        TODO("Not yet implemented")
    }

    override fun validateRequestCode(request: ValidateOtpCodeRequest) {
        TODO("Not yet implemented")
    }
}