package org.sound.hive.android.model.history

import org.sound.hive.android.model.Song

data class HistoryState(
    val songs: List<Song> = emptyList(),
    val isLoading: Boolean = false
)