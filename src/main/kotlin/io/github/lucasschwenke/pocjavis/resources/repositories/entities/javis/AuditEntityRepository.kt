package io.github.lucasschwenke.pocjavis.resources.repositories.entities.javis

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface AuditEntityRepository : JpaRepository<AuditEntity, String> {

    @Query("SELECT a FROM AuditEntity a where a.revision.revision = :revisionNumber and a.name = :name")
    fun findByRevisionNumberAndName(
        @Param("revisionNumber") revisionNumber: Int,
        @Param("name") name: String
    ): AuditEntity?

    fun deleteByRevisionAndName(
        @Param("revision.revision") revisionNumber: Int,
        @Param("name") name: String
    )
}
