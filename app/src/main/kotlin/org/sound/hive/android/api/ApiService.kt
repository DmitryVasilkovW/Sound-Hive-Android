package org.sound.hive.android.api

import org.sound.hive.android.model.User

interface ApiService {
    suspend fun getUser(userId: String)
}