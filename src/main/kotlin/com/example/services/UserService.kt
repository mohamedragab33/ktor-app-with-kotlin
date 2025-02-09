package com.example.com.example.services

import com.example.com.example.exceptions.UserNotFoundException
import com.example.com.example.models.User
import com.example.com.example.models.UserReq
import com.example.repositories.UserRepository

import mu.KotlinLogging
import java.util.UUID


private val logger = KotlinLogging.logger {}

class UserService(private val userRepository: UserRepository) {
    fun getAllUsers(): List<User> {
        logger.info { "Fetching all users" }
        return userRepository.getAllUsers()
    }

    fun getUserById(id: UUID): User {
        logger.info { "Fetching user with ID $id" }
        return userRepository.getUserById(id) ?: throw UserNotFoundException("User with ID $id not found")
    }

    fun createUser(user: UserReq) = userRepository.createUser(user)

    fun updateUser(id: UUID, user: User) = userRepository.updateUser(id,user)

    fun deleteUser(id: UUID) = userRepository.deleteUser(id)
}