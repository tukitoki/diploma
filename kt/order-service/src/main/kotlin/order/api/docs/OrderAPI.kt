package ru.vsu.cs.raspopov.order.api.docs

import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "Order API", description = "Order API для работы с заказами")
interface OrderAPI {

    fun getAllOrders()

    fun getOrderById(id: Long)

    fun confirmOrder(id: Long)

    fun cancelOrder(id: Long)

}