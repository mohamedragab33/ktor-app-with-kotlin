package com.example.com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class UserReq(
    val name: String,
    val email: String
)