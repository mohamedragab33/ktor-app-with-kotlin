package com.example.com.example.controllers

import com.example.com.example.models.User
import com.example.com.example.models.UserReq
import com.example.com.example.services.UserService
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.*

class UserController(private val userService: UserService) {
    fun Route.getAllUsers() {
        get {
            val users = userService.getAllUsers()
            call.respond(users)
        }
    }

    fun Route.getUserById() {
        get("/{id}") {
            val id = call.parameters["id"]?.let { UUID.fromString(it) }
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "Invalid ID")
                return@get
            }
            val user = userService.getUserById(id)
            call.respond(user)
        }
    }

    fun Route.createUser() {
        post {
            val user = call.receive<UserReq>()
            userService.createUser(user)
            call.respond(HttpStatusCode.Created, user)
        }
    }

    fun Route.updateUser() {
        put("/{id}") {
            val id = call.parameters["id"]?.let { UUID.fromString(it) }
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "Invalid ID")
                return@put
            }
            val user = call.receive<User>()
            userService.updateUser(id, user)
            call.respond(HttpStatusCode.OK, user)
        }
    }

    fun Route.deleteUser() {
        delete("/{id}") {
            val id = call.parameters["id"]?.let { UUID.fromString(it) }
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "Invalid ID")
                return@delete
            }
            userService.deleteUser(id)
            call.respond(HttpStatusCode.NoContent)
        }
    }
}