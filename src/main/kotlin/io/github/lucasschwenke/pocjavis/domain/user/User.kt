package io.github.lucasschwenke.pocjavis.domain.user

import java.time.LocalDate

data class User(
    val id: String? = null,
    val name: String,
    val birthdate: LocalDate,
    val age: Int,
    val gender: Gender
)
