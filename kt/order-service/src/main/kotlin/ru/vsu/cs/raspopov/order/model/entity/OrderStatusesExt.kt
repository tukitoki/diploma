package ru.vsu.cs.raspopov.order.model.entity

import ru.vsu.cs.raspopov.common.exception.GeneralException
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