package io.github.lucasschwenke.pocjavis.domain.services

import io.github.lucasschwenke.pocjavis.domain.repositories.UserRepository
import io.github.lucasschwenke.pocjavis.domain.user.User
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun create(user: User): User = userRepository.save(user)

    fun update(userId: String, user: User): User {
        return userRepository.findById(userId)?.let {
            val newUser = it.copy(
                name = user.name,
                birthdate = user.birthdate,
                age = user.age,
                gender = user.gender
            )

            userRepository.update(newUser)
        } ?: throw RuntimeException("Deu pau")
    }
}
