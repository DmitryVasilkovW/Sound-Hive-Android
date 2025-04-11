package org.sound.hive.android.data.repository

import org.sound.hive.android.model.Song

interface SongsRepository {
    suspend fun getSongs(): List<Song>
}