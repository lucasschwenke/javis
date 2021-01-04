package io.github.lucasschwenke.pocjavis.domain.repositories

import io.github.lucasschwenke.pocjavis.resources.repositories.entities.javis.AuditEntity

interface JavisRepository {

    fun getAll(): List<AuditEntity>

    fun findByRevisionRevAndName(revisionRev: Int, name: String): AuditEntity?

    fun deleteById(id: String)
}