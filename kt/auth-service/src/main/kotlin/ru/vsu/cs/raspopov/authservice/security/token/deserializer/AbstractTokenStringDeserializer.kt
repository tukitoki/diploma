package ru.vsu.cs.raspopov.authservice.security.token.deserializer

import ru.vsu.cs.raspopov.authservice.tokens.model.entity.Token

sealed class AbstractTokenStringDeserializer<out T : Token?> : (String) -> (Result<T>)