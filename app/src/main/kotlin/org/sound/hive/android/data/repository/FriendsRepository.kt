package org.sound.hive.android.data.repository

import org.sound.hive.android.model.Friend

interface FriendsRepository {
    suspend fun getFriends(): List<Friend>
}
