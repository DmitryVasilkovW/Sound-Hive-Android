package org.sound.hive.android.data.repository

import kotlinx.coroutines.test.*
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.mockito.kotlin.*
import org.sound.hive.android.api.*
import org.sound.hive.android.data.repository.impl.*
import org.sound.hive.android.model.*

class SongsRepositoryTest {

    private val songApi = mock<SongApi>()

    private lateinit var repository: SongsRepositoryImpl

    private val song1 = Song("111", "Track 1", "Artist 1")
    private val song2 = Song("222", "Track 2", "Artist 2")

    @BeforeEach
    fun setup() {
        repository = SongsRepositoryImpl(songApi)
    }

    @Test
    fun `getSongs returns songs from first album if not empty`() = runTest {
        whenever(songApi.getSongsByAlbum("111362")).thenReturn(listOf(song1, song2))

        val songs = repository.getSongs()

        assertEquals(2, songs.size)
        assertEquals(song1, songs[0])
        verify(songApi).getSongsByAlbum("111362")
        verify(songApi, never()).getSongsByAlbum("2118628")
    }

    @Test
    fun `getSongs returns songs from second album if first empty`() = runTest {
        whenever(songApi.getSongsByAlbum("111362")).thenReturn(emptyList())
        whenever(songApi.getSongsByAlbum("2118628")).thenReturn(listOf(song2))

        val songs = repository.getSongs()

        assertEquals(1, songs.size)
        assertEquals(song2, songs[0])
        verify(songApi).getSongsByAlbum("111362")
        verify(songApi).getSongsByAlbum("2118628")
    }

    @Test
    fun `getSongs returns empty list on exception`() = runTest {
        whenever(songApi.getSongsByAlbum(any())).thenThrow(RuntimeException("API failure"))

        val songs = repository.getSongs()

        assertTrue(songs.isEmpty())
    }
}
