package org.sound.hive.android.viewModel.abstracts

import androidx.lifecycle.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.sound.hive.android.model.*
import org.sound.hive.android.service.SongService

abstract class BaseSongListScreenViewModel<Intent, Action>(
    protected val songService: SongService,
) : ViewModel(), SongListScreenViewModel {

    protected val stateMutable = MutableStateFlow(SongListScreenState())
    override val state: StateFlow<SongListScreenState> = stateMutable.asStateFlow()

    override suspend fun getSongs(): List<Song> = songService.getAllSongs()

    override fun processIntent(intent: Any) {
        @Suppress("UNCHECKED_CAST")
        val action = processIntentInternal(intent as Intent)
        processAction(action)
    }

    protected abstract fun processIntentInternal(intent: Intent): Action

    protected abstract fun processAction(action: Action)

    protected fun loadInitialData(loadSongsAction: Action) {
        viewModelScope.launch {
            stateMutable.update { it.copy(isLoading = true) }
            processAction(loadSongsAction)
        }
    }

    protected fun loadSongs() {
        viewModelScope.launch {
            stateMutable.update { it.copy(isLoading = true) }
            val songs = getSongs()
            stateMutable.update {
                it.copy(
                    songs = songs,
                    isLoading = false
                )
            }
        }
    }

    protected abstract fun navigate()
}
