package org.sound.hive.android.service

import kotlinx.coroutines.test.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import org.sound.hive.android.data.repository.*
import org.sound.hive.android.model.*
import org.sound.hive.android.service.impl.*

class FriendsServiceTest {

    private val fakeFriends = listOf(
        Friend(photoResId = 0, name = "Alice", song = "Song A", artist = "Artist A"),
        Friend(photoResId = 0, name = "Bob", song = "Song B", artist = "Artist B"),
        Friend(photoResId = 0, name = "Charlie", song = "Song C", artist = "Artist C")
    )

    private val repository = mock<FriendsRepository> {
        onBlocking { getFriends() } doReturn fakeFriends
    }

    private val service = FriendsServiceImpl(repository)

    @Test
    fun `getAllFriends returns all friends`() = runTest {
        val result = service.getAllFriends()
        assertEquals(fakeFriends, result)
    }

    @Test
    fun `getFriendById returns correct friend`() = runTest {
        val friend = service.getFriendById("Bob")
        assertNotNull(friend)
        assertEquals("Bob", friend?.name)
    }

    @Test
    fun `getFriendById returns null when not found`() = runTest {
        val friend = service.getFriendById("Unknown")
        assertNull(friend)
    }

    @Test
    fun `addFriend returns false`() = runTest {
        val result = service.addFriend(fakeFriends.first())
        assertFalse(result)
    }

    @Test
    fun `removeFriend returns false`() = runTest {
        val result = service.removeFriend("Alice")
        assertFalse(result)
    }

    @Test
    fun `getFriendsWithSimilarTaste returns 3 or fewer friends`() = runTest {
        val result = service.getFriendsWithSimilarTaste()
        assertTrue(result.size in 1..3)
        assertTrue(result.all { it in fakeFriends })
    }
}
