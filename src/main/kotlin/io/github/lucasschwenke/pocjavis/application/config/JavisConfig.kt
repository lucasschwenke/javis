package io.github.lucasschwenke.pocjavis.application.config

import org.springframework.context.annotation.Configuration
import java.sql.Connection

@Configuration
class JavisConfig {

    fun configDatabase(connection: Connection) {
        try {
            val stmt = connection.createStatement()
            stmt.execute(CREATE_FIRST_TABLE)
            stmt.execute(CREATE_SECOND_TABLE)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            connection.close()
        }
    }

    companion object {
        private const val CREATE_FIRST_TABLE =
            "CREATE TABLE IF NOT EXISTS revisions(\n" +
                "    rev BIGINT PRIMARY KEY,\n" +
                "    rev_hash varchar(255)\n" +
                ");"

        private const val CREATE_SECOND_TABLE =
            "CREATE TABLE IF NOT EXISTS audits(\n" +
                "    id varchar(255) NOT NULL PRIMARY KEY,\n" +
                "    revision_number BIGINT NOT NULL REFERENCES revisions(rev),\n" +
                "    name varchar(255) NOT NULL,\n" +
                "    simple_class_name varchar(255) NOT NULL,\n" +
                "    qualified_class_name varchar(255) NOT NULL,\n" +
                "    audit_type varchar(100) NOT NULL,\n" +
                "    data TEXT NOT NULL,\n" +
                "    author varchar(255) NOT NULL,\n" +
                "    created_at timestamp NOT NULL\n" +
                ");"
    }
}