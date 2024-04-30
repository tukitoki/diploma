package ru.vsu.cs.raspopov.authservice.cache.model.repo

import org.springframework.data.repository.CrudRepository
import ru.vsu.cs.raspopov.authservice.cache.model.OtpCodeEntity

interface OtpCodeRepository : CrudRepository<OtpCodeEntity, String> {
}