package org.sound.hive.android.data.repository

import org.sound.hive.android.model.*

interface UserRepository {
    suspend fun getUserById(id: Long): User
}
