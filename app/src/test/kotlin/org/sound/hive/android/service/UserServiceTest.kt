package org.sound.hive.android.service

import kotlinx.coroutines.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.sound.hive.android.api.*
import org.sound.hive.android.data.repository.*
import org.sound.hive.android.model.*
import org.sound.hive.android.service.impl.*

class UserServiceTest {

    private val userRepository = mock(UserRepository::class.java)
    private val apiService = mock(ApiService::class.java)
    private val userService = UserServiceImpl(userRepository, apiService)

    @Test
    fun `getUserById returns user from repository`() = runBlocking {
        val userId = 123L
        val expectedUser = User(name = "Test User", email = "test@example.com")
        `when`(userRepository.getUserById(userId)).thenReturn(expectedUser)

        val actualUser = userService.getUserById(userId.toString())

        assertNotNull(actualUser)
        assertEquals(expectedUser.name, actualUser?.name)
        assertEquals(expectedUser.email, actualUser?.email)
    }

    @Test
    fun `getUserById returns null when exception thrown`() = runBlocking {
        val userId = 123L
        `when`(userRepository.getUserById(userId)).thenThrow(RuntimeException("DB error"))

        val actualUser = userService.getUserById(userId.toString())

        assertNull(actualUser)
    }

    @Test
    fun `getCurrentUser returns current user`() = runBlocking {
        val currentUser = userService.getCurrentUser()
        assertNotNull(currentUser)
        assertEquals("Current User", currentUser?.name)
        assertEquals("user@example.com", currentUser?.email)
    }

    @Test
    fun `updateUser returns true`() = runBlocking {
        val user = User(name = "New Name", email = "new@example.com")
        val result = userService.updateUser(user)
        assertTrue(result)
    }
}
