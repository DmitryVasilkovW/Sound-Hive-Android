package org.sound.hive.android.api

import org.sound.hive.android.model.Song

interface SongApi {
    suspend fun getSongsByAlbum(albumId: String): List<Song>
    suspend fun getSongById(songId: String): Song?
    suspend fun getSongByMusicBrainzId(mbId: String): Song?
}