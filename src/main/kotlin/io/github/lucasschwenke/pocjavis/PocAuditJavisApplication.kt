package io.github.lucasschwenke.pocjavis

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
class PocAuditJavisApplication

fun main(args: Array<String>) {
	runApplication<PocAuditJavisApplication>(*args)
}
