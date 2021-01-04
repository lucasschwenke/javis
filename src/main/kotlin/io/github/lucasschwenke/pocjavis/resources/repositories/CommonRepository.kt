package io.github.lucasschwenke.pocjavis.resources.repositories

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Component
class CommonRepository {

    @PersistenceContext
    private lateinit var em: EntityManager

    @Transactional
    fun <E> update(entity: E) {
        em.merge(entity)
    }
}