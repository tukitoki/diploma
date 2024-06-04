package ru.vsu.cs.raspopov.client.autoService.dto

import ru.vsu.cs.raspopov.order.model.entity.Order

data class OrderCreateMessage(
    val orderId: Long,
    val detailIds: Set<Long>,
    val carId: Long,
    val windowId: Long,
    val works: Set<Long>,
    val customerId: Long,
) {
    constructor(order: Order) : this(
        orderId = order.id.value,
        detailIds = order.orderDetails.map { it.detailId }.toSet(),
        carId = order.carId,
        windowId = order.reservedWindowId,
        works = order.orderWorks.map { it.workId }.toSet(),
        customerId = order.customerId,
    )
}