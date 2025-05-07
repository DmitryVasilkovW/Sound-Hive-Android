package org.sound.hive.android.model.room.dao

import androidx.room.*
import org.sound.hive.android.model.room.entity.*

@Dao
interface SongDao {
    @Query("SELECT * FROM songs WHERE idTrack = :id")
    suspend fun getSongById(id: String): SongEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSong(song: SongEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSongs(songs: List<SongEntity>)

    @Delete
    suspend fun deleteSong(song: SongEntity)

    @Query("DELETE FROM songs WHERE idTrack = :id")
    suspend fun deleteSongById(id: String)
}
