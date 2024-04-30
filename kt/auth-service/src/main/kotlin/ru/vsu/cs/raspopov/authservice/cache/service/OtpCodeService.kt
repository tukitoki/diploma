package ru.vsu.cs.raspopov.authservice.cache.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ru.vsu.cs.raspopov.authservice.cache.model.dto.request.SaveOtpCodeRequest
import ru.vsu.cs.raspopov.authservice.cache.model.repo.OtpCodeRepository

@Service
class OtpCodeService(
    private val repository: OtpCodeRepository,
) {

    fun saveOtpCode(
        request: SaveOtpCodeRequest,
    ) {
        val entity = request.toEntity()
        repository.save(entity)
    }

    fun findById(
        id: String,
    ) = repository.findByIdOrNull(id)
}