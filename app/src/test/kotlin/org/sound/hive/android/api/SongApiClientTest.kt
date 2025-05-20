package org.sound.hive.android.api

import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.sound.hive.android.model.Song
import org.sound.hive.android.model.SongResponse

class SongApiClientTest {

    private lateinit var mockEngine: MockEngine
    private lateinit var httpClient: HttpClient
    private lateinit var songApiClient: SongApiClient

    @BeforeEach
    fun setup() {
        mockEngine = MockEngine { request ->
            when {
                // Mock response for getSongsByAlbum
                request.url.toString().contains("/track.php") && request.url.parameters["m"] != null -> {
                    val albumId = request.url.parameters["m"]
                    respond(
                        content = """
                            {
                                "track": [
                                    {
                                        "idTrack": "1",
                                        "idAlbum": "$albumId",
                                        "strTrack": "Test Track 1",
                                        "strArtist": "Test Artist"
                                    },
                                    {
                                        "idTrack": "2",
                                        "idAlbum": "$albumId",
                                        "strTrack": "Test Track 2",
                                        "strArtist": "Test Artist"
                                    }
                                ]
                            }
                        """.trimIndent(),
                        status = HttpStatusCode.OK,
                        headers = headersOf(HttpHeaders.ContentType, "application/json")
                    )
                }
                // Mock response for getSongById
                request.url.toString().contains("/track.php") && request.url.parameters["h"] != null -> {
                    val songId = request.url.parameters["h"]
                    if (songId == "valid_id") {
                        respond(
                            content = """
                                {
                                    "track": [
                                        {
                                            "idTrack": "$songId",
                                            "strTrack": "Test Track",
                                            "strArtist": "Test Artist"
                                        }
                                    ]
                                }
                            """.trimIndent(),
                            status = HttpStatusCode.OK,
                            headers = headersOf(HttpHeaders.ContentType, "application/json")
                        )
                    } else {
                        respond(
                            content = """
                                {
                                    "track": null
                                }
                            """.trimIndent(),
                            status = HttpStatusCode.OK,
                            headers = headersOf(HttpHeaders.ContentType, "application/json")
                        )
                    }
                }
                // Mock response for getSongByMusicBrainzId
                request.url.toString().contains("/track-mb.php") && request.url.parameters["i"] != null -> {
                    val mbId = request.url.parameters["i"]
                    if (mbId == "valid_mbid") {
                        respond(
                            content = """
                                {
                                    "track": [
                                        {
                                            "idTrack": "3",
                                            "strTrack": "Test Track MB",
                                            "strArtist": "Test Artist",
                                            "strMusicBrainzID": "$mbId"
                                        }
                                    ]
                                }
                            """.trimIndent(),
                            status = HttpStatusCode.OK,
                            headers = headersOf(HttpHeaders.ContentType, "application/json")
                        )
                    } else {
                        respond(
                            content = """
                                {
                                    "track": null
                                }
                            """.trimIndent(),
                            status = HttpStatusCode.OK,
                            headers = headersOf(HttpHeaders.ContentType, "application/json")
                        )
                    }
                }
                // Handle error case
                else -> {
                    respond(
                        content = "Not found",
                        status = HttpStatusCode.NotFound,
                        headers = headersOf(HttpHeaders.ContentType, "text/plain")
                    )
                }
            }
        }

        httpClient = HttpClient(mockEngine) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                })
            }
        }

        songApiClient = SongApiClient(httpClient)
    }

    @Test
    fun `getSongsByAlbum returns list of songs for valid album id`() = runBlocking {
        // Given
        val albumId = "111362"

        // When
        val songs = songApiClient.getSongsByAlbum(albumId)

        // Then
        assertEquals(2, songs.size)
        assertEquals(albumId, songs[0].idAlbum)
        assertEquals("Test Track 1", songs[0].strTrack)
        assertEquals("Test Artist", songs[0].strArtist)
    }

    @Test
    fun `getSongById returns song for valid id`() = runBlocking {
        // Given
        val songId = "valid_id"

        // When
        val song = songApiClient.getSongById(songId)

        // Then
        assertNotNull(song)
        assertEquals(songId, song?.idTrack)
        assertEquals("Test Track", song?.strTrack)
        assertEquals("Test Artist", song?.strArtist)
    }

    @Test
    fun `getSongById returns null for invalid id`() = runBlocking {
        // Given
        val songId = "invalid_id"

        // When
        val song = songApiClient.getSongById(songId)

        // Then
        assertNull(song)
    }

    @Test
    fun `getSongByMusicBrainzId returns song for valid mbid`() = runBlocking {
        // Given
        val mbId = "valid_mbid"

        // When
        val song = songApiClient.getSongByMusicBrainzId(mbId)

        // Then
        assertNotNull(song)
        assertEquals("3", song?.idTrack)
        assertEquals("Test Track MB", song?.strTrack)
        assertEquals("Test Artist", song?.strArtist)
        assertEquals(mbId, song?.strMusicBrainzID)
    }

    @Test
    fun `getSongByMusicBrainzId returns null for invalid mbid`() = runBlocking {
        // Given
        val mbId = "invalid_mbid"

        // When
        val song = songApiClient.getSongByMusicBrainzId(mbId)

        // Then
        assertNull(song)
    }
}