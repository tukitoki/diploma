package ru.vsu.cs.raspopov.client.autoService.dto.messages

import ru.vsu.cs.raspopov.order.model.dto.request.OrderUpdateRequest

data class OrderUpdateMessage(
    val id: Long,
    val windowId: Long,
    val serviceId: Long?,
) {
    constructor(request: OrderUpdateRequest) : this(
        id = request.orderId,
        windowId = request.windowId,
        serviceId = request.serviceId,
    )
}