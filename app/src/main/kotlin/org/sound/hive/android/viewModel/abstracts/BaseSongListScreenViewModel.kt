package org.sound.hive.android.viewModel.abstracts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.sound.hive.android.effect.HomeSideEffect
import org.sound.hive.android.model.Song
import org.sound.hive.android.model.SongListScreenState
import org.sound.hive.android.data.repository.SongsRepository

abstract class BaseSongListScreenViewModel<Intent, Action>(
    protected val songsRepository: SongsRepository,
) : ViewModel(), SongListScreenViewModel {

    protected val stateMutable = MutableStateFlow(SongListScreenState())
    override val state: StateFlow<SongListScreenState> = stateMutable.asStateFlow()

    override fun getSongs(): List<Song> = songsRepository.getSongs()

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

    protected abstract fun navigate();
}
