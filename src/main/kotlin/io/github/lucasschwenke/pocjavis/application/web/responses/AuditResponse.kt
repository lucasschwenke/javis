package io.github.lucasschwenke.pocjavis.application.web.responses

import io.github.lucasschwenke.pocjavis.domain.services.Audit
import io.github.lucasschwenke.pocjavis.resources.repositories.entities.javis.AuditEntity
import java.time.LocalDateTime

data class AuditResponse(
    val revision: Int,
    val type: String,
    val data: Any,
    val author: String,
    val createdAt: LocalDateTime
) {

    constructor(auditEntity: Audit): this(
        revision = auditEntity.revision.revision,
        type = auditEntity.auditType,
        data = auditEntity.data,
        author = auditEntity.author,
        createdAt = auditEntity.createdAt
    )
}

data class AuditsResponse(
    val audits: List<AuditResponse>
)
