package ru.vsu.cs.raspopov.order.service.impl.useCases.result

import org.jetbrains.exposed.sql.Op
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.vsu.cs.raspopov.client.autoService.dto.AutoServiceResponse
import ru.vsu.cs.raspopov.client.autoService.dto.OperationResult
import ru.vsu.cs.raspopov.order.model.entity.cancel
import ru.vsu.cs.raspopov.order.model.entity.pending
import ru.vsu.cs.raspopov.order.service.impl.OrderService

@Transactional
@Service
class OrderCheckoutResultUseCase(
    private val orderService: OrderService,
) {

    fun invoke(response: AutoServiceResponse) {
        val order = orderService.findThrowableOrderById(response.orderId, Op.nullOp())

        when (response.operationResult) {
            OperationResult.FAIL -> order.cancel()

            OperationResult.SUCCESS -> order.pending()
        }
    }
}