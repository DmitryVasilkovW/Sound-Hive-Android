package org.sound.hive.android.data.repository.impl

import org.sound.hive.android.R
import org.sound.hive.android.data.repository.FriendsRepository
import org.sound.hive.android.model.Friend
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FriendsRepositoryImpl @Inject constructor() : FriendsRepository {
    override suspend fun getFriends(): List<Friend> {
        return listOf(
            Friend(
                photoResId = R.drawable.ic_avatar_default_light,
                name = "Alice",
                song = "Honestly?",
                artist = "American Football",
            ),
            Friend(
                photoResId = R.drawable.ic_avatar_default_light,
                name = "Bob",
                song = "Gonna Leave You",
                artist = "Queens of the Stone Age",
            )
        )
    }
}
