package io.github.lucasschwenke.pocjavis.resources.repositories.entities.javis

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "revisions")
data class RevisionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rev")
    val revision: Int = 0,

    @Column(name = "rev_hash")
    val revisionHash: String
)
