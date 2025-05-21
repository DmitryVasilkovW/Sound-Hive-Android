package org.sound.hive.android.data.repository

import kotlinx.coroutines.*
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.sound.hive.android.data.repository.impl.*

class UserRepositoryTest {

    private val userRepository = UserRepositoryImpl()

    @Test
    fun `getUserById returns expected user`() = runBlocking {
        val user = userRepository.getUserById(1L)
        assertEquals("Bazis", user.name)
        assertEquals("tmp@hive.com", user.email)
    }
}
