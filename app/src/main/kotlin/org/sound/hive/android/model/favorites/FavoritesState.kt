package org.sound.hive.android.model.favorites

import org.sound.hive.android.model.Song

data class FavoritesState(
    val songs: List<Song> = emptyList(),
    val isLoading: Boolean = false
)