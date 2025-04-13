package org.sound.hive.android.viewModel.abstracts

import kotlinx.coroutines.flow.StateFlow
import org.sound.hive.android.model.Song
import org.sound.hive.android.model.SongListScreenState

interface SongListScreenViewModel {
    val state: StateFlow<SongListScreenState>
    fun processIntent(intent: Any)
    fun getSongs(): List<Song>
}
