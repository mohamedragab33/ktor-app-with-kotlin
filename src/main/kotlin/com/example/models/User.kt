package com.example.com.example.models


import com.example.com.example.config.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class User(
    @Serializable(with = UUIDSerializer::class) val id: UUID,
    val name: String,
    val email: String
)