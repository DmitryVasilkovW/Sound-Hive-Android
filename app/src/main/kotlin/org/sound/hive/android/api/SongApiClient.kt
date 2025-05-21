package org.sound.hive.android.api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import org.sound.hive.android.model.*

class SongApiClient(
    private val httpClient: HttpClient
) : SongApi {

    private companion object {
        const val BASE_URL = "https://www.theaudiodb.com/api/v1/json/2"
    }

    override suspend fun getSongsByAlbum(albumId: String): List<Song> {
        val songs = httpClient.get("$BASE_URL/track.php") {
            parameter("m", albumId)
        }.body<SongResponse>()
            .track ?: emptyList()

        return songs.map {
            getAlbumThumb(it)
        }
    }

    override suspend fun getSongById(songId: String): Song? {
        val song = httpClient.get("$BASE_URL/track.php") {
            parameter("h", songId)
        }.body<SongResponse>()
            .track?.firstOrNull()

        return song?.let { getAlbumThumb(it) }
    }

    override suspend fun getSongByMusicBrainzId(mbId: String): Song? {
        val song = httpClient.get("$BASE_URL/track-mb.php") {
            parameter("i", mbId)
        }.body<SongResponse>()
            .track?.firstOrNull()

        return song?.let { getAlbumThumb(it) }
    }

    private suspend fun getAlbumThumb(song: Song): Song {
        val album = httpClient.get("$BASE_URL/album.php") {
            parameter("m", song.idAlbum)
        }.body<AlbumResponse>()
            .album?.firstOrNull()

        return song.apply {
            strTrackThumb = album?.thumbnail
        }
    }
}
