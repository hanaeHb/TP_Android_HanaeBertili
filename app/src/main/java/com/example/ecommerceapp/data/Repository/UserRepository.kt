package com.example.ecommerceapp.data.Repository

object UserRepository {
    private val registeredUsers = mutableMapOf<String, String>()

    fun registerUser(email: String, password: String): Boolean {
        if (registeredUsers.containsKey(email)) return false
        registeredUsers[email] = password
        return true
    }

    fun isValidUser(email: String, password: String): Boolean {
        return registeredUsers[email] == password
    }
}
