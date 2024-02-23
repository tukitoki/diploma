package ru.vsu.cs.raspopov.authservice.security.token.serializer

import ru.vsu.cs.raspopov.authservice.model.redis.Token

sealed class AbstractTokenStringSerializer<in T : Token> : (T) -> Result<String>