package com.example.lista2

object UserManager {

    private val users = mutableListOf(
        User("user1", "password1"),
        User("user2", "password2"),
        User("user3", "password3"),
        User("user4", "password4"),
        User("user5", "password5")
    )

    fun getUsers(): List<User> = users

    fun login(username: String, password: String): User? {
        return users.find { it.username == username && it.password == password }
    }

    fun addUser(user: User): Boolean {
        val existingUser = users.find { it.username == user.username }
        return if (existingUser == null) {
            users.add(user)
            true
        } else {
            false
        }
    }
}