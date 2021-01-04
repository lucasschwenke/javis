package io.github.lucasschwenke.pocjavis.application.config

import org.flywaydb.core.Flyway
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class MigrationsConfig {

    @Bean
    fun runMigrations(dataSource: DataSource, javisConfig: JavisConfig): Flyway =
        Flyway.configure()
            .locations("db/migrations")
            .dataSource(dataSource)
            .schemas("default_schema")
            .load()
            .also {
                it.migrate()
            }.also {
                javisConfig.configDatabase(dataSource.connection)
            }
}
