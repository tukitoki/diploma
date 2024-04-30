package ru.vsu.cs.raspopov.authservice.security.token.factory

import ru.vsu.cs.raspopov.authservice.tokens.model.entity.Token

sealed class AbstractTokenFactory<in T, out S : Token>(
    protected val tokenTtl: Long,
) : (T) -> (S)