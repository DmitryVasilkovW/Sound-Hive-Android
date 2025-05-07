package org.sound.hive.android.api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import org.sound.hive.android.model.Song
import org.sound.hive.android.model.SongResponse

class SongApiClient(
    private val httpClient: HttpClient
) : SongApi {

    private companion object {
        const val BASE_URL = "https://www.theaudiodb.com/api/v1/json/2"
    }

    override suspend fun getSongsByAlbum(albumId: String): List<Song> {
        return httpClient.get("$BASE_URL/track.php") {
            parameter("m", albumId)
        }.body<SongResponse>()
            .song ?: emptyList()
    }

    override suspend fun getSongById(songId: String): Song? {
        return httpClient.get("$BASE_URL/track.php") {
            parameter("h", songId)
        }.body<SongResponse>()
            .song?.firstOrNull()
    }

    override suspend fun getSongByMusicBrainzId(mbId: String): Song? {
        return httpClient.get("$BASE_URL/track-mb.php") {
            parameter("i", mbId)
        }.body<SongResponse>()
            .song?.firstOrNull()
    }
}