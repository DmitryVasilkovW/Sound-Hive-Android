package org.sound.hive.android.service

import org.sound.hive.android.model.Song

interface SongService {
    suspend fun getAllSongs(): List<Song>
    suspend fun getSongById(songId: String): Song?
    suspend fun getSongsByAlbum(albumId: String): List<Song>
    suspend fun getSongByMusicBrainzId(mbId: String): Song?
    suspend fun saveSong(song: Song): Boolean
    suspend fun deleteSong(songId: String): Boolean
}