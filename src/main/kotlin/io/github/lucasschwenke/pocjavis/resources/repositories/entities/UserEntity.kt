package io.github.lucasschwenke.pocjavis.resources.repositories.entities

import io.github.lucasschwenke.pocjavis.application.javis.Auditable
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
@Auditable(name = "users")
data class UserEntity(
    @Id @GeneratedValue(generator = "uuid") @GenericGenerator(name = "uuid", strategy = "uuid2") val id: String? = null,
    val name: String,
    val birthdate: LocalDate,
    val age: Int,
    val gender: GenderEntity,
    @Column(name  = "created_at") @CreationTimestamp val createdAt: LocalDateTime = LocalDateTime.now(),
    @Column(name  = "updated_at") @UpdateTimestamp val updateAt: LocalDateTime? = null
)
