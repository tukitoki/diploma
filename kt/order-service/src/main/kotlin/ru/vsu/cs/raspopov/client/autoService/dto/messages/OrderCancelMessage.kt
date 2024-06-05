package ru.vsu.cs.raspopov.client.autoService.dto.messages

import ru.vsu.cs.raspopov.order.model.dto.request.OrderCancelRequest

data class OrderCancelMessage(
    val id: Long,
) {
    constructor(request: OrderCancelRequest) : this(
        request.id
    )
}