package io.github.lucasschwenke.pocjavis.application.web.controllers

import io.github.lucasschwenke.pocjavis.application.web.requests.UserRequest
import io.github.lucasschwenke.pocjavis.application.web.requests.extensions.toUser
import io.github.lucasschwenke.pocjavis.application.web.responses.UserResponse
import io.github.lucasschwenke.pocjavis.domain.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService
) {

    @PostMapping
    @ResponseBody
    fun create(
        @RequestBody userRequest: UserRequest
    ): ResponseEntity<UserResponse> =
        ResponseEntity(UserResponse(userService.create(userRequest.toUser()).id!!), HttpStatus.CREATED)

    @PutMapping("/{userId}")
    @ResponseBody
    fun update(
        @PathVariable("userId") userId: String,
        @RequestBody userRequest: UserRequest
    ): ResponseEntity<UserResponse> =
        ResponseEntity(UserResponse(userService.update(userId, userRequest.toUser()).id!!), HttpStatus.OK)
}
