package io.github.lucasschwenke.pocjavis.application.web.requests

import io.github.lucasschwenke.pocjavis.domain.user.Gender
import java.time.LocalDate

data class UserRequest(
    val name: String,
    val birthdate: LocalDate,
    val age: Int,
    val gender: Gender
)
