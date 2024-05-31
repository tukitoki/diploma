package ru.vsu.cs.raspopov.orderDetail.model.table

import ru.vsu.cs.raspopov.exposed.TimeAtLongIdTable
import ru.vsu.cs.raspopov.order.model.table.Orders

object OrderDetails : TimeAtLongIdTable() {

    val detailId = long("detail_id")
    val price = double("price")

    val orderId = reference("order_id", Orders)
}