package org.sound.hive.android.data.repository.impl

import org.sound.hive.android.api.SongApi
import org.sound.hive.android.data.repository.SongsRepository
import org.sound.hive.android.model.Song
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SongsRepositoryImpl @Inject constructor(
    private val songApi: SongApi
) : SongsRepository {
    override suspend fun getSongs(): List<Song> {
        try {
            val songs = songApi.getSongsByAlbum("111362")
            if (songs.isNotEmpty()) {
                return songs
            }

            return songApi.getSongsByAlbum("2118628")
        } catch (e: Exception) {
            e.printStackTrace()
            return emptyList()
        }
    }
}
