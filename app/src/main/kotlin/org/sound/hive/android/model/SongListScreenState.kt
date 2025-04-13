package org.sound.hive.android.model

data class SongListScreenState(
    val songs: List<Song> = emptyList(),
    val isLoading: Boolean = false
)
