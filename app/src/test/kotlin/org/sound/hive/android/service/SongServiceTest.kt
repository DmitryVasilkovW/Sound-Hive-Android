package org.sound.hive.android.service

import kotlinx.coroutines.test.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import org.sound.hive.android.api.*
import org.sound.hive.android.data.repository.*
import org.sound.hive.android.data.room.*
import org.sound.hive.android.model.*
import org.sound.hive.android.model.room.dao.*
import org.sound.hive.android.model.room.entity.*
import org.sound.hive.android.service.impl.*

class SongServiceTest {

    private val songsRepository = mock<SongsRepository>()
    private val songApi = mock<SongApi>()
    private val appDatabase = mock<AppDatabase>()
    private val songDao = mock<SongDao>()

    private lateinit var songService: SongServiceImpl

    private val testSong = Song(
        idTrack = "1",
        strTrack = "Test Track",
        strArtist = "Test Artist"
    )
    private val testSongEntity = SongEntity(
        idTrack = "1",
        strTrack = "Test Track",
        strArtist = "Test Artist"
    )

    @BeforeEach
    fun setup() {
        whenever(appDatabase.songDao()).thenReturn(songDao)

        songService = SongServiceImpl(
            songsRepository = songsRepository,
            songApi = songApi,
            appDatabase = appDatabase
        )
    }

    @Test
    fun `getAllSongs returns list from repository`() = runTest {
        whenever(songsRepository.getSongs()).thenReturn(listOf(testSong))

        val result = songService.getAllSongs()

        assertEquals(1, result.size)
        assertEquals(testSong, result[0])
        verify(songsRepository).getSongs()
    }

    @Test
    fun `getSongById returns song from database if present`() = runTest {
        whenever(songDao.getSongById("1")).thenReturn(testSongEntity)

        val result = songService.getSongById("1")

        assertNotNull(result)
        assertEquals(testSong.idTrack, result?.idTrack)
        assertEquals(testSong.strTrack, result?.strTrack)
        assertEquals(testSong.strArtist, result?.strArtist)

        verify(songDao).getSongById("1")
        verifyNoInteractions(songApi)
    }

    @Test
    fun `getSongById fetches from API if not in database`() = runTest {
        whenever(songDao.getSongById("1")).thenReturn(null)
        whenever(songApi.getSongById("1")).thenReturn(testSong)

        val result = songService.getSongById("1")

        assertEquals(testSong, result)

        verify(songDao).getSongById("1")
        verify(songApi).getSongById("1")
    }

    @Test
    fun `getSongById returns null on API exception`() = runTest {
        whenever(songDao.getSongById("1")).thenReturn(null)
        whenever(songApi.getSongById("1")).thenThrow(RuntimeException("API failure"))

        val result = songService.getSongById("1")

        assertNull(result)
    }

    @Test
    fun `getSongsByAlbum returns songs from API`() = runTest {
        whenever(songApi.getSongsByAlbum("album123")).thenReturn(listOf(testSong))

        val result = songService.getSongsByAlbum("album123")

        assertEquals(1, result.size)
        assertEquals(testSong, result[0])
    }

    @Test
    fun `getSongsByAlbum returns empty list on API exception`() = runTest {
        whenever(songApi.getSongsByAlbum("album123")).thenThrow(RuntimeException("API failure"))

        val result = songService.getSongsByAlbum("album123")

        assertTrue(result.isEmpty())
    }

    @Test
    fun `saveSong returns false on exception`() = runTest {
        whenever(appDatabase.songDao()).thenReturn(songDao)
        doThrow(RuntimeException("DB failure")).whenever(songDao).insertSong(any())

        val result = songService.saveSong(testSong)

        assertFalse(result)
    }

    @Test
    fun `deleteSong returns false on exception`() = runTest {
        doThrow(RuntimeException("DB failure")).whenever(songDao).deleteSongById("1")

        val result = songService.deleteSong("1")

        assertFalse(result)
    }

    @Test
    fun `getSongByMusicBrainzId returns song from API`() = runTest {
        whenever(songApi.getSongByMusicBrainzId("mbid")).thenReturn(testSong)

        val result = songService.getSongByMusicBrainzId("mbid")

        assertEquals(testSong, result)
    }

    @Test
    fun `getSongByMusicBrainzId returns null on exception`() = runTest {
        whenever(songApi.getSongByMusicBrainzId("mbid")).thenThrow(RuntimeException("API failure"))

        val result = songService.getSongByMusicBrainzId("mbid")

        assertNull(result)
    }
}
