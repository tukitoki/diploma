package ru.vsu.cs.raspopov.authservice.security.token.deserializer

import ru.vsu.cs.raspopov.authservice.model.redis.Token

sealed class AbstractTokenStringDeserializer<out T : Token?> : (String) -> (Result<T>)