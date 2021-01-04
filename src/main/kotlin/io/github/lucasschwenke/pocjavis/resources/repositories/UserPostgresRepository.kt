package io.github.lucasschwenke.pocjavis.resources.repositories

import io.github.lucasschwenke.pocjavis.domain.repositories.UserRepository
import io.github.lucasschwenke.pocjavis.domain.user.Gender
import io.github.lucasschwenke.pocjavis.domain.user.User
import io.github.lucasschwenke.pocjavis.resources.repositories.entities.GenderEntity
import io.github.lucasschwenke.pocjavis.resources.repositories.entities.UserEntity
import io.github.lucasschwenke.pocjavis.resources.repositories.entities.extensions.toUserEntity
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
@Transactional
class UserPostgresRepository(
    private val repository: UserJPARepository
): UserRepository {

    override fun findById(userId: String): User? {
        return repository.findById(userId).orElse(null)?.let {
            it.toUser()
        }
    }

    override fun save(user: User): User =
        repository.save(user.toUserEntity()).let {
            user.copy(id = it.id!!)
        }

    override fun update(user: User): User =
        repository.save(user.toUserEntityWithId()).let {
            user.copy(id = it.id!!)
        }
}

fun UserEntity.toUser() = User(
    id = this.id,
    name = this.name,
    birthdate = this.birthdate,
    age = this.age,
    gender = Gender.valueOf(this.gender.name)
)

fun User.toUserEntityWithId() =
    UserEntity(
        id = this.id!!,
        name = this.name,
        birthdate = this.birthdate,
        age = this.age,
        gender = GenderEntity.valueOf(this.gender.name)
    )
