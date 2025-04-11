package org.sound.hive.android.viewModel

import org.sound.hive.android.model.Song

interface BaseViewModel {
    suspend fun processIntent(intent: Any)
    fun getSongs(): List<Song>
}