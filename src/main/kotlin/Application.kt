package com.example

import com.example.com.example.config.DatabaseFactory
import com.example.com.example.controllers.UserController
import com.example.com.example.di.appModule
import com.example.com.example.exceptions.InvalidUserException
import com.example.com.example.exceptions.UserNotFoundException
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.http.*
import mu.KotlinLogging
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

private val logger = KotlinLogging.logger {}

fun Application.module() {
    configureDatabase()
    configureKoin()
    configureStatusPages()
    configureAppRouting()
    logger.info { "Starting Ktor application..." }
}

private fun Application.configureDatabase() {
    DatabaseFactory.init()
}

private fun Application.configureKoin() {
    install(Koin) {
        modules(appModule)
    }
}

private fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<UserNotFoundException> { call, cause ->
            call.respond(HttpStatusCode.NotFound, cause.message ?: "User not found")
        }
        exception<InvalidUserException> { call, cause ->
            call.respond(HttpStatusCode.BadRequest, cause.message ?: "Invalid user data")
        }
        exception<Throwable> { call, cause ->
            call.respond(HttpStatusCode.InternalServerError, cause.message ?: "Internal server error")
        }
    }
}

private fun Application.configureAppRouting() {
    val userController: UserController by inject()
    routing {
        route("/users") {
            userController.apply {
                getAllUsers()
                getUserById()
                createUser()
                updateUser()
                deleteUser()
            }
        }
    }
}