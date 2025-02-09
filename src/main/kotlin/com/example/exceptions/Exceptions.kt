package com.example.com.example.exceptions

// src/main/kotlin/com/example/exceptions/Exceptions.kt
class UserNotFoundException(message: String) : RuntimeException(message)
class InvalidUserException(message: String) : RuntimeException(message)