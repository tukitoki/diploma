package ru.vsu.cs.raspopov.authservice.security.token.serializer

import ru.vsu.cs.raspopov.authservice.tokens.model.entity.Token

sealed class AbstractTokenStringSerializer<in T : Token> : (T) -> Result<String>