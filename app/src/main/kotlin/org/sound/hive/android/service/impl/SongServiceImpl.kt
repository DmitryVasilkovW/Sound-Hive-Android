package org.sound.hive.android.service.impl

import org.sound.hive.android.api.*
import org.sound.hive.android.data.repository.*
import org.sound.hive.android.data.room.*
import org.sound.hive.android.model.*
import org.sound.hive.android.model.room.entity.*
import org.sound.hive.android.service.*
import javax.inject.*

@Singleton
class SongServiceImpl @Inject constructor(
    private val songsRepository: SongsRepository,
    private val songApi: SongApi,
    private val appDatabase: AppDatabase
) : SongService {
    override suspend fun getAllSongs(): List<Song> {
        return songsRepository.getSongs()
    }

    override suspend fun getSongById(songId: String): Song? {
        val songDao = appDatabase.songDao()
        val songEntity = songDao.getSongById(songId)
        if (songEntity != null) {
            return convertEntityToModel(songEntity)
        }

        try {
            return songApi.getSongById(songId)
        } catch (e: Exception) {

            return null
        }
    }

    override suspend fun getSongsByAlbum(albumId: String): List<Song> {
        return try {
            songApi.getSongsByAlbum(albumId)
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getSongByMusicBrainzId(mbId: String): Song? {
        return try {
            songApi.getSongByMusicBrainzId(mbId)
        } catch (e: Exception) {

            null
        }
    }

    override suspend fun saveSong(song: Song): Boolean {
        return try {
            val songDao = appDatabase.songDao()
            val songEntity = convertModelToEntity(song)
            songDao.insertSong(songEntity)
            true
        } catch (e: Exception) {

            false
        }
    }

    override suspend fun deleteSong(songId: String): Boolean {
        return try {
            val songDao = appDatabase.songDao()
            songDao.deleteSongById(songId)
            true
        } catch (e: Exception) {

            false
        }
    }

    private fun convertEntityToModel(entity: SongEntity): Song {
        return Song(
            idTrack = entity.idTrack,
            strTrack = entity.strTrack,
            strArtist = entity.strArtist
        )
    }

    private fun convertModelToEntity(model: Song): SongEntity {
        return SongEntity(
            idTrack = model.idTrack,
            strTrack = model.strTrack,
            strArtist = model.strArtist
        )
    }
}
