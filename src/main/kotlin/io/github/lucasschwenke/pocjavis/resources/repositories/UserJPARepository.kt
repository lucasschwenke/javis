package io.github.lucasschwenke.pocjavis.resources.repositories

import io.github.lucasschwenke.pocjavis.resources.repositories.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserJPARepository: JpaRepository<UserEntity, String>
