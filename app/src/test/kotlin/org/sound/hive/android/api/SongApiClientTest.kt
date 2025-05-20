package org.sound.hive.android.api

import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.test.*
import kotlinx.serialization.json.*
import org.junit.jupiter.api.Test
import kotlin.test.*

class SongApiClientTest {

    private fun createMockClient(responseJson: String): HttpClient {
        val mockEngine = MockEngine { request ->
            respond(
                content = responseJson,
                status = HttpStatusCode.OK,
                headers = headersOf("Content-Type" to listOf(ContentType.Application.Json.toString()))
            )
        }

        return HttpClient(mockEngine) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
        }
    }

    @Test
    fun `getSongsByAlbum returns list of songs`() = runTest {
        val json = """
            {
              "track": [
                {
                  "idTrack": "123",
                  "strTrack": "Test Song"
                }
              ]
            }
        """.trimIndent()

        val client = SongApiClient(createMockClient(json))

        val result = client.getSongsByAlbum("456")
        assertEquals(1, result.size)
        assertEquals("123", result[0].idTrack)
        assertEquals("Test Song", result[0].strTrack)
    }

    @Test
    fun `getSongById returns first song`() = runTest {
        val json = """
            {
              "track": [
                {
                  "idTrack": "789",
                  "strTrack": "Another Song"
                }
              ]
            }
        """.trimIndent()

        val client = SongApiClient(createMockClient(json))
        val result = client.getSongById("789")

        assertEquals("789", result?.idTrack)
        assertEquals("Another Song", result?.strTrack)
    }

    @Test
    fun `getSongsByAlbum returns empty list if track is null`() = runTest {
        val json = """{ "track": null }"""
        val client = SongApiClient(createMockClient(json))
        val result = client.getSongsByAlbum("456")
        assertEquals(emptyList(), result)
    }

    @Test
    fun `getSongById returns null if track is null`() = runTest {
        val json = """{ "track": null }"""
        val client = SongApiClient(createMockClient(json))
        val result = client.getSongById("456")
        assertNull(result)
    }
}
