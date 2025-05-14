package org.sound.hive.android.service.impl

import org.sound.hive.android.api.ApiService
import org.sound.hive.android.data.repository.UserRepository
import org.sound.hive.android.model.User
import org.sound.hive.android.service.UserService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserServiceImpl @Inject constructor(
    private val userRepository: UserRepository,
    private val apiService: ApiService
) : UserService {
    override suspend fun getUserById(userId: String): User? {
        try {
            return userRepository.getUserById(userId.toLong())
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    override suspend fun getCurrentUser(): User? {
        return try {
            User(name = "Current User", email = "user@example.com")
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun updateUser(user: User): Boolean {
        return try {
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}
