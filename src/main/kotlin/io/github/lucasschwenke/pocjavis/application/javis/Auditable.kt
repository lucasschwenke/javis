package io.github.lucasschwenke.pocjavis.application.javis

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Auditable(val name: String)