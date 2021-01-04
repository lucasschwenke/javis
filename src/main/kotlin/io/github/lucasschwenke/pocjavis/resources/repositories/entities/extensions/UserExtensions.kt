package io.github.lucasschwenke.pocjavis.resources.repositories.entities.extensions

import io.github.lucasschwenke.pocjavis.domain.user.User
import io.github.lucasschwenke.pocjavis.resources.repositories.entities.GenderEntity
import io.github.lucasschwenke.pocjavis.resources.repositories.entities.UserEntity

fun User.toUserEntity() =
    UserEntity(
        name = this.name,
        birthdate = this.birthdate,
        age = this.age,
        gender = GenderEntity.valueOf(this.gender.name)
    )
