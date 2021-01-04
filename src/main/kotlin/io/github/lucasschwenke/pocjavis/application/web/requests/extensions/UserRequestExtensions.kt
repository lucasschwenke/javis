package io.github.lucasschwenke.pocjavis.application.web.requests.extensions

import io.github.lucasschwenke.pocjavis.application.web.requests.UserRequest
import io.github.lucasschwenke.pocjavis.domain.user.User

fun UserRequest.toUser() =
    User(
        name = this.name,
        birthdate = this.birthdate,
        age = this.age,
        gender = this.gender
    )
