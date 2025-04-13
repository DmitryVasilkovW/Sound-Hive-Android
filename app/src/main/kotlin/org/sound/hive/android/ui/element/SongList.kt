package org.sound.hive.android.ui.element

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.*
import org.sound.hive.android.viewModel.abstracts.BaseViewModel

@Composable
fun SongList(
    onSongClick: () -> Unit,
    viewModel: BaseViewModel
) {
    val state by viewModel.state.collectAsState()
    val songs = state.songs
    val isLoading = state.isLoading

    if (isLoading) {
        LoadingIndicator()
    } else {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(songs) { index, song ->
                SongItem(song = song, index = index + 1, onSongClick)
            }
        }
    }
}

@Composable
private fun LoadingIndicator() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}
