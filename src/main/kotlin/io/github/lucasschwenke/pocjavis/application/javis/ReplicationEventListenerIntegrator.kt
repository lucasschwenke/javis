package io.github.lucasschwenke.pocjavis.application.javis

import org.hibernate.event.service.spi.EventListenerRegistry
import org.hibernate.event.spi.EventType
import org.hibernate.event.spi.PostDeleteEventListener
import org.hibernate.event.spi.PostInsertEventListener
import org.hibernate.event.spi.PostUpdateEventListener
import org.hibernate.internal.SessionFactoryImpl
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct
import javax.persistence.EntityManagerFactory
import javax.persistence.PersistenceUnit

@Component
class ReplicationEventListenerIntegrator(
    private val entityEventListener: AuditLogEventListener
) {

    @PersistenceUnit
    private lateinit var emf: EntityManagerFactory

    @PostConstruct
    fun registerListeners() {
        val sessionFactory = emf.unwrap(SessionFactoryImpl::class.java)
        val registry =
            (sessionFactory as SessionFactoryImpl).serviceRegistry.getService(EventListenerRegistry::class.java)

        registry.getEventListenerGroup(EventType.POST_INSERT).appendListener(entityEventListener as PostInsertEventListener)
        registry.getEventListenerGroup(EventType.POST_UPDATE).appendListener(entityEventListener as PostUpdateEventListener)
        registry.getEventListenerGroup(EventType.POST_DELETE).appendListener(entityEventListener as PostDeleteEventListener)
    }
}