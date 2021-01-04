package io.github.lucasschwenke.pocjavis.resources.repositories.entities.javis

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "audits")
data class AuditEntity(
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    val id: String? = null,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "revision_number")
    val revision: RevisionEntity,

    @Column(name = "name")
    val name: String,

    @Column(name = "simple_class_name")
    val simpleClassName: String,

    @Column(name = "qualified_class_name")
    val qualifiedClassName: String,

    @Column(name = "audit_type")
    val auditType: String,

    @Column(name = "data")
    val data: String,

    @Column(name = "author")
    val author: String,

    @Column(name = "created_at")
    @CreationTimestamp
    val createdAt: LocalDateTime? = LocalDateTime.now()
)
