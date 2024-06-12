package ru.vsu.cs.raspopov.orderAudit.service

import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener
import ru.vsu.cs.raspopov.orderAudit.model.dto.request.AuditEvent

@Service
class OrderAuditListener {

    @EventListener
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun auditOrderAction(auditEvent: AuditEvent) {}
}