package org.sound.hive.android.data.repository

import kotlinx.coroutines.test.*
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.sound.hive.android.data.repository.impl.*

class FriendsRepositoryTest {

    private val repository = FriendsRepositoryImpl()

    @Test
    fun `getFriends returns expected friends`() = runTest {
        val friends = repository.getFriends()
        assertEquals(2, friends.size)
        assertEquals("Alice", friends[0].name)
        assertEquals("Bob", friends[1].name)
    }
}
