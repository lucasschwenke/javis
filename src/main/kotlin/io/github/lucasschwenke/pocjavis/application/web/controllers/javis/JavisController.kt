package io.github.lucasschwenke.pocjavis.application.web.controllers.javis

import io.github.lucasschwenke.pocjavis.application.web.responses.AuditResponse
import io.github.lucasschwenke.pocjavis.application.web.responses.AuditsResponse
import io.github.lucasschwenke.pocjavis.domain.services.JavisService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/audit")
class JavisController(
    private val javisService: JavisService
) {

    @GetMapping("/{name}")
    @ResponseBody
    fun getAll(@PathVariable("name") name: String): ResponseEntity<AuditsResponse> {
        val result = javisService.getAll(name)
        val list = result.map { AuditResponse(it) }
        return ResponseEntity.ok(AuditsResponse(list))
    }

    @GetMapping("/{name}/revision/{revision}")
    @ResponseBody
    fun getByRevision(
        @PathVariable("name") name: String,
        @PathVariable("revision") revision: Int
    ): ResponseEntity<AuditResponse> {
        return javisService.findByRevisionRevAndName(revision, name)?.let {
            ResponseEntity.ok(AuditResponse(it))
        } ?: ResponseEntity.notFound().build()
    }

    @PutMapping("/{name}/revision/{revision}/rollback")
    @ResponseBody
    fun rollback(
        @PathVariable("name") name: String,
        @PathVariable("revision") revision: Int
    ): ResponseEntity<AuditResponse> {
        return javisService.rollback(revision, name)?.let {
            ResponseEntity.ok(AuditResponse(it))
        } ?: ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{name}/revision/{revision}")
    @ResponseBody
    fun deleteByRevision(
        @PathVariable("name") name: String,
        @PathVariable("revision") revision: Int
    ): ResponseEntity<Any> {
        return javisService.delete(revision, name).let {
            ResponseEntity.noContent().build()
        }
    }
}
