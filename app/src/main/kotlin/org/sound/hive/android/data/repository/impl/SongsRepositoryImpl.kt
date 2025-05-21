package org.sound.hive.android.data.repository.impl

import org.sound.hive.android.api.*
import org.sound.hive.android.data.repository.*
import org.sound.hive.android.model.*
import javax.inject.*

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
            return emptyList()
        }
    }
}
