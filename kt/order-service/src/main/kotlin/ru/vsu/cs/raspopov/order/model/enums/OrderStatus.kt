package ru.vsu.cs.raspopov.order.model.enums

enum class OrderStatus {
    TEMPORARY,
    CONFIRMED,
    CANCELED,
    SUCCESSFUL,
    PENDING,
    APPROVED,
    NOT_SUCCESSFUL,
    ;

    fun isCancellableStatus() = this in listOf(PENDING, APPROVED, CONFIRMED)

    fun isApproveStatus() = this == PENDING

    fun isConfirmableStatus() = this == PENDING

    fun isCanBeCheckout() = this == PENDING

    fun isScheduled() = this in listOf(APPROVED, CONFIRMED)
}