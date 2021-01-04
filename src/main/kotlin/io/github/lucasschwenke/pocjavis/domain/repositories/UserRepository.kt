package io.github.lucasschwenke.pocjavis.domain.repositories

import io.github.lucasschwenke.pocjavis.domain.user.User

interface UserRepository {

    fun findById(userId: String): User?

    fun save(user: User): User

    fun update(user: User): User
}