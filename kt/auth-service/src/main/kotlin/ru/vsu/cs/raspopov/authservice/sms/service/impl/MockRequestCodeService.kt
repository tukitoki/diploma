package ru.vsu.cs.raspopov.authservice.sms.service.impl

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Service
import ru.vsu.cs.raspopov.authservice.cache.model.dto.request.SaveOtpCodeRequest
import ru.vsu.cs.raspopov.authservice.cache.service.OtpCodeService
import ru.vsu.cs.raspopov.authservice.exception.GeneralException
import ru.vsu.cs.raspopov.authservice.sms.model.dto.request.SendOtpCodeRequest
import ru.vsu.cs.raspopov.authservice.sms.model.dto.request.ValidateOtpCodeRequest
import ru.vsu.cs.raspopov.authservice.sms.service.IRequestCodeService

@ConditionalOnProperty(name = ["service.mock"], havingValue = "true")
@Service
class MockRequestCodeService(
    private val otpCodeService: OtpCodeService,
) : IRequestCodeService {

    override fun sendRequestCode(request: SendOtpCodeRequest) {
        otpCodeService.findById(request.phone)?.let {
            throw GeneralException("Повторная отправка возможна через ${it.ttl} секунд")
        }

        otpCodeService.saveOtpCode(
            SaveOtpCodeRequest(
                request.phone,
                MOCK_CODE,
            )
        )
    }

    override fun validateRequestCode(request: ValidateOtpCodeRequest) {
        otpCodeService.findById(request.phone)
            ?: throw GeneralException("Отсутствует код по номеру телефона")
    }

    private companion object {
        const val MOCK_CODE = "1111"
    }
}