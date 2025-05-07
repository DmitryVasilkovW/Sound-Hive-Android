package org.sound.hive.android.service.impl

import org.sound.hive.android.data.repository.FriendsRepository
import org.sound.hive.android.model.Friend
import org.sound.hive.android.service.FriendsService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FriendsServiceImpl @Inject constructor(
    private val friendsRepository: FriendsRepository
) : FriendsService {

    override suspend fun getAllFriends(): List<Friend> {
        return friendsRepository.getFriends()
    }

    override suspend fun getFriendById(friendId: String): Friend? {
        return friendsRepository.getFriends()
            .find { it.name == friendId }
    }

    override suspend fun addFriend(friend: Friend): Boolean {
        return false
    }

    override suspend fun removeFriend(friendId: String): Boolean {
        return false
    }

    override suspend fun getFriendsWithSimilarTaste(): List<Friend> {
        return friendsRepository.getFriends()
            .shuffled()
            .take(3)
    }
}
