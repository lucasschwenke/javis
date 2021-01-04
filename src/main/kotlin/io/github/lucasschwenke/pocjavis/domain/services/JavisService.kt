package io.github.lucasschwenke.pocjavis.domain.services

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.lucasschwenke.pocjavis.domain.repositories.JavisRepository
import io.github.lucasschwenke.pocjavis.resources.repositories.CommonRepository
import io.github.lucasschwenke.pocjavis.resources.repositories.entities.javis.RevisionEntity
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class JavisService(
    private val javisRepository: JavisRepository,
    private val objectMapper: ObjectMapper,
    private val commonRepository: CommonRepository
) {

    fun getAll(name: String): List<Audit> {
        val resultDb = javisRepository.getAll()

        return resultDb.map {
            val classz = Class.forName(it.qualifiedClassName)
            val readValue = objectMapper.readValue(it.data, classz)
            Audit(
                id = it.id,
                revision = it.revision,
                name = it.name,
                simpleClassName = it.simpleClassName,
                qualifiedClassName = it.qualifiedClassName,
                auditType = it.auditType,
                data = readValue,
                author = it.author,
                createdAt = it.createdAt!!
            )
        }
    }

    fun findByRevisionRevAndName(revisionRev: Int, name: String): Audit? {
        return javisRepository.findByRevisionRevAndName(revisionRev, name)?.let {
            val classz = Class.forName(it.qualifiedClassName)
            val readValue = objectMapper.readValue(it.data, classz)
            Audit(
                id = it.id,
                revision = it.revision,
                name = it.name,
                simpleClassName = it.simpleClassName,
                qualifiedClassName = it.qualifiedClassName,
                auditType = it.auditType,
                data = readValue,
                author = it.author,
                createdAt = it.createdAt!!
            )
        }
    }

    fun delete(revisionRev: Int, name: String) {
        javisRepository.findByRevisionRevAndName(revisionRev, name)?.run {
            javisRepository.deleteById(this.id!!)
        }
    }

    fun rollback(revisionRev: Int, name: String): Audit? {
        return javisRepository.findByRevisionRevAndName(revisionRev, name)?.let {
            val classz = Class.forName(it.qualifiedClassName)
            val readValue = objectMapper.readValue(it.data, classz)
            commonRepository.update(readValue)

            Audit(
                id = it.id,
                revision = it.revision,
                name = it.name,
                simpleClassName = it.simpleClassName,
                qualifiedClassName = it.qualifiedClassName,
                auditType = it.auditType,
                data = readValue,
                author = it.author,
                createdAt = it.createdAt!!
            )
        }
    }
}

data class Audit(
    val id: String? = null,
    val revision: RevisionEntity,
    val name: String,
    val simpleClassName: String,
    val qualifiedClassName: String,
    val auditType: String,
    val data: Any,
    val author: String,
    val createdAt: LocalDateTime
)