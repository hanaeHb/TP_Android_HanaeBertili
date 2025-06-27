package com.example.ecommerceapp.data.Repository

import androidx.compose.runtime.mutableStateListOf
import com.example.ecommerceapp.data.Entities.User

object UserRepository {
    private val registeredUsers = mutableMapOf<String, User>()
    private val users = mutableStateListOf<User>()
    private val deletedUsers = mutableSetOf<String>()

    sealed class RegisterResult {
        object Success : RegisterResult()
        object AlreadyRegistered : RegisterResult()
        object Blocked : RegisterResult()
    }

    fun registerUser(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
        phone: String
    ): RegisterResult {
        if (deletedUsers.contains(email)) return RegisterResult.Blocked
        if (registeredUsers.containsKey(email)) return RegisterResult.AlreadyRegistered

        val user = User(email, password, firstName, lastName, phone)
        registeredUsers[email] = user
        users.add(user)
        return RegisterResult.Success
    }

    fun isValidUser(email: String, password: String): Boolean {
        if (deletedUsers.contains(email)) return false
        val user = registeredUsers[email]
        return user?.password == password
    }

    fun isBlocked(email: String): Boolean {
        return deletedUsers.contains(email)
    }

    fun deleteUser(user: User) {
        registeredUsers.remove(user.email)
        users.remove(user)
        deletedUsers.add(user.email)
    }

    fun getAllUsers(): List<User> = registeredUsers.values.toList()

    fun getUserByEmailAndPassword(email: String, password: String): User? {
        val user = registeredUsers[email]
        return if (user != null && user.password == password && !deletedUsers.contains(email)) {
            user
        } else {
            null
        }
    }
}