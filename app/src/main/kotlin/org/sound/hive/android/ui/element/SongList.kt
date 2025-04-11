package org.sound.hive.android.ui.element

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import org.sound.hive.android.viewModel.BaseViewModel

@Composable
fun SongList(
    onSongClick: () -> Unit,
    viewModel: BaseViewModel,
) {
    val songs = viewModel.getSongs()

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(songs) { index, song ->
            SongItem(song = song, index = index + 1, onSongClick)
        }
    }
}
