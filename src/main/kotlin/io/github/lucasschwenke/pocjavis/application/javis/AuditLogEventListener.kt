package io.github.lucasschwenke.pocjavis.application.javis

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.github.lucasschwenke.pocjavis.resources.repositories.entities.javis.AuditEntity
import io.github.lucasschwenke.pocjavis.resources.repositories.entities.javis.AuditEntityRepository
import io.github.lucasschwenke.pocjavis.resources.repositories.entities.javis.RevisionEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.hibernate.event.spi.PostDeleteEvent
import org.hibernate.event.spi.PostDeleteEventListener
import org.hibernate.event.spi.PostInsertEvent
import org.hibernate.event.spi.PostInsertEventListener
import org.hibernate.event.spi.PostUpdateEvent
import org.hibernate.event.spi.PostUpdateEventListener
import org.hibernate.persister.entity.EntityPersister
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.math.BigInteger
import java.security.MessageDigest
import java.time.LocalDateTime

@Component
class AuditLogEventListener(
    private val repository: AuditEntityRepository,
    private val objectMapper: ObjectMapper
): PostUpdateEventListener, PostInsertEventListener, PostDeleteEventListener {

    override fun requiresPostCommitHanding(persister: EntityPersister): Boolean {
        return true
    }

    override fun onPostUpdate(event: PostUpdateEvent) {
        event.entity.javaClass.getAnnotation(Auditable::class.java)?.run {
            try {
                AuditEntity(
                    revision = RevisionEntity(revisionHash = generateMD5()),
                    name = this.name,
                    simpleClassName = event.entity::class.simpleName.toString(),
                    qualifiedClassName = event.entity::class.qualifiedName.toString(),
                    auditType = AudityType.UPDATE.name,
                    data = objectMapper.writeValueAsString(event.entity),
                    author = "Lucas"
                ).also {
                    GlobalScope.launch {
                        save(it)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onPostInsert(event: PostInsertEvent) {
        event.entity.javaClass.getAnnotation(Auditable::class.java)?.run {
            val entity = event.entity::class
            try {
                AuditEntity(
                    revision = RevisionEntity(revisionHash = generateMD5()),
                    name = this.name,
                    simpleClassName = entity.simpleName.toString(),
                    qualifiedClassName = entity.qualifiedName.toString(),
                    auditType = AudityType.INSERT.name,
                    data = objectMapper.writeValueAsString(event.entity),
                    author = "Lucas"
                ).also {
//                    GlobalScope.launch {
//                        save(it)
//                    }
                    try {
                        repository.save(it)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onPostDelete(event: PostDeleteEvent) {
        event.entity.javaClass.getAnnotation(Auditable::class.java)?.run {
            try {
                AuditEntity(
                    revision = RevisionEntity(revisionHash = generateMD5()),
                    name = this.name,
                    simpleClassName = event.entity::class.simpleName.toString(),
                    qualifiedClassName = event.entity::class.qualifiedName.toString(),
                    auditType = AudityType.DELETE.name,
                    data = objectMapper.writeValueAsString(event.entity),
                    author = "Lucas"
                ).also {
                    GlobalScope.launch {
                        save(it)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    //@Transactional
    private fun save(auditEntity: AuditEntity) {
        try {

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

fun generateMD5(): String {
    val md = MessageDigest.getInstance("MD5")
    val now = LocalDateTime.now()
    return BigInteger(1, md.digest(now.toString().toByteArray())).toString(16).padStart(32, '0')
}

enum class AudityType{
    INSERT,
    UPDATE,
    DELETE
}