package org.sound.hive.android.service

import org.sound.hive.android.model.User

interface UserService {
    suspend fun getUserById(userId: String): User?
    suspend fun getCurrentUser(): User?
    suspend fun updateUser(user: User): Boolean
}