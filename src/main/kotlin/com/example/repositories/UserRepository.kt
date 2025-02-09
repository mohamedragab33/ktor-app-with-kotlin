package com.example.repositories


import com.example.com.example.dao.UserTable
import com.example.com.example.models.User
import com.example.com.example.models.UserReq
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID

class UserRepository {
    fun getAllUsers(): List<User> = transaction {
        UserTable.selectAll().map { toUser(it) }
    }

    fun getUserById(id: UUID): User? = transaction {
        UserTable.select { UserTable.id eq id }
            .mapNotNull { toUser(it) }
            .singleOrNull()
    }

    fun createUser(user: UserReq): User = transaction {
        val id = UserTable.insertAndGetId {
            it[name] = user.name
            it[email] = user.email
        }.value
        User(id = id, name = user.name, email = user.email)
    }

    fun updateUser(id: UUID, user: User): Boolean = transaction {
        UserTable.update({ UserTable.id eq id }) {
            it[name] = user.name
            it[email] = user.email
        } > 0
    }

    fun deleteUser(id: UUID): Boolean = transaction {
        UserTable.deleteWhere { UserTable.id eq id } > 0
    }

    private fun toUser(row: ResultRow): User =
        User(
            id = row[UserTable.id].value,
            name = row[UserTable.name],
            email = row[UserTable.email]
        )
}