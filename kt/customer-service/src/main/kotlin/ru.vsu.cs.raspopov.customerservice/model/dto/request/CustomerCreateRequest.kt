package ru.vsu.cs.raspopov.customerservice.model.dto.request

import ru.vsu.cs.raspopov.user.dto.request.UserCreateRequest

data class CustomerCreateRequest(
    // TODO: implement more properly
    val userDto: UserCreateRequest,
)