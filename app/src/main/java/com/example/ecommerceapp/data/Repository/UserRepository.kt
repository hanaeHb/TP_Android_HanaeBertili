package com.example.ecommerceapp.data.Repository

import com.example.ecommerceapp.data.Entities.User

object UserRepository {
    private val registeredUsers = mutableMapOf<String, User>()

    fun registerUser(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
        phone: String
    ): Boolean {
        if (registeredUsers.containsKey(email)) return false
        val user = User(email, password, firstName, lastName, phone)
        registeredUsers[email] = user
        return true
    }

    fun isValidUser(email: String, password: String): Boolean {
        val user = registeredUsers[email]
        return user?.password == password
    }

    fun getUser(email: String): User? {
        return registeredUsers[email]
    }
}