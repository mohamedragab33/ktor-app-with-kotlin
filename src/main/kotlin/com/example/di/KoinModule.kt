package com.example.com.example.di

import com.example.com.example.controllers.UserController

import com.example.com.example.services.UserService
import com.example.repositories.UserRepository
import org.koin.dsl.module


val appModule = module {
    single { UserRepository() } // Provides a single instance of UserRepository
    single { UserService(get()) } // Injects UserRepository into UserService
    single { UserController(get()) } // Injects UserService into UserController
}