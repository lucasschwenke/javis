package io.github.lucasschwenke.pocjavis.resources.repositories

import io.github.lucasschwenke.pocjavis.domain.repositories.JavisRepository
import io.github.lucasschwenke.pocjavis.resources.repositories.entities.javis.AuditEntity
import io.github.lucasschwenke.pocjavis.resources.repositories.entities.javis.AuditEntityRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class AuditPostgresRepository(
    private val auditEntityRepository: AuditEntityRepository
): JavisRepository {

    override fun getAll(): List<AuditEntity> {
        val findAll = auditEntityRepository.findAll()
        return findAll.toList()
    }

    override fun findByRevisionRevAndName(revisionRev: Int, name: String): AuditEntity? {
        return auditEntityRepository.findByRevisionNumberAndName(revisionRev, name)
    }

    override fun deleteById(id: String) {
        return auditEntityRepository.deleteById(id)
    }
}
