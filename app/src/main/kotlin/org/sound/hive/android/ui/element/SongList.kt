package org.sound.hive.android.ui.element

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.*
import androidx.compose.ui.*

@Composable
fun SongList() {
    val songs = remember { songsExample }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(songs) { _, songList ->
            for ((index, song) in songList.withIndex()) {
                SongItem(song = song, index = index + 1)
            }
        }
    }
}
