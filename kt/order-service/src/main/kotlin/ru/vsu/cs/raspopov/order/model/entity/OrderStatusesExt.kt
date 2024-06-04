package ru.vsu.cs.raspopov.order.model.entity

import ru.vsu.cs.raspopov.common.exception.GeneralException
import ru.vsu.cs.raspopov.order.model.dto.request.OrderUpdateRequest
import ru.vsu.cs.raspopov.order.model.enums.OrderStatus

fun Order.checkout() {
    if (this.status.isCanBeCheckout().not()) {
        throw GeneralException("Order can't be checkout due status")
    }

    this.status = OrderStatus.CONFIRMED
}

fun Order.confirm() {
    if (this.status.isApproveStatus().not()) {
        throw GeneralException("Order can't be approved due status")
    }

    this.status = OrderStatus.CONFIRMED
}

fun Order.updateAfterCheckout(request: OrderUpdateRequest) {
    if (this.status.isCanBeUpdate().not()) {
        throw GeneralException("Order can't be updated due status")
    }

    this.reservedWindowId = request.windowId
    request.serviceId?.let { this.carServiceId = it }
    this.status = OrderStatus.PENDING
}

fun Order.cancel() {
    if (this.status.isCancellableStatus().not()) {
        throw GeneralException("Order can't be cancelled due status")
    }

    this.status = OrderStatus.CANCELED
}