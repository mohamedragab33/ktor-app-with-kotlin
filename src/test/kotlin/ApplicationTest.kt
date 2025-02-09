package com.example

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun testGetAllUsers() = testApplication {
        val response = client.get("/users")
        assertEquals(HttpStatusCode.OK, response.status)
    }
}