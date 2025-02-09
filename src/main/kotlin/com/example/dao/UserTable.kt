package com.example.com.example.dao

import org.jetbrains.exposed.dao.id.UUIDTable

object UserTable : UUIDTable() {
    val name = varchar("name", 50)
    val email = varchar("email", 50).uniqueIndex()
}

