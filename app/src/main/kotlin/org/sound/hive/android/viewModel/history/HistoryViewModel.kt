package org.sound.hive.android.viewModel.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.sound.hive.android.action.HistoryAction
import org.sound.hive.android.data.repository.SongsRepository
import org.sound.hive.android.effect.HomeSideEffect
import org.sound.hive.android.intent.HistoryIntent
import org.sound.hive.android.model.Song
import org.sound.hive.android.model.history.HistoryState
import org.sound.hive.android.ui.common.homeRoute
import org.sound.hive.android.viewModel.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val songsRepository: SongsRepository
) : ViewModel(), BaseViewModel {
    private val stateMutable = MutableStateFlow(HistoryState())
    val state: StateFlow<HistoryState> = stateMutable.asStateFlow()

    private val sideEffectMutable = MutableSharedFlow<HomeSideEffect>()

    init {
        processIntentUtilFunc(HistoryIntent.LoadInitialData)
    }

    override suspend fun processIntent(intent: Any) {
        processIntentUtilFunc(intent as HistoryIntent)
    }

    override fun getSongs(): List<Song> = songsRepository.getSongs()

    fun processIntentUtilFunc(intent: HistoryIntent) {
        val action = when (intent) {
            is HistoryIntent.LoadInitialData -> HistoryAction.LoadInitialData
            is HistoryIntent.NavigateToHome -> HistoryAction.Navigate(homeRoute)
            is HistoryIntent.UseFilters -> HistoryAction.UseFilter("Last listened")
        }

        processAction(action)
    }

    private fun processAction(action: HistoryAction) {
        when (action) {
            is HistoryAction.LoadInitialData -> loadInitialData()
            is HistoryAction.Navigate -> navigate(action.route)
            is HistoryAction.LoadSongs -> loadSongs()
            is HistoryAction.UseFilter -> useFilters(action.filterType)
        }
    }


    private fun loadInitialData() {
        viewModelScope.launch {
            stateMutable.update { it.copy(isLoading = true) }
            processAction(HistoryAction.LoadSongs)
        }
    }

    private fun loadSongs() {
        viewModelScope.launch {
            stateMutable.update { it.copy(isLoading = true) }
            val songs = songsRepository.getSongs()
            stateMutable.update {
                it.copy(
                    songs = songs,
                    isLoading = false
                )
            }
        }
    }

    private fun navigate(route: String) {
        viewModelScope.launch {
            sideEffectMutable.emit(HomeSideEffect.Navigate(route))
        }
    }

    private fun useFilters(filterType: String) {
        viewModelScope.launch {
            stateMutable.update { it.copy(isLoading = true) }
            var songs = emptyList<Song>()
            songs = if (filterType == "By Song") {
                songsRepository.getSongs().sortedBy { it.title }
            } else if (filterType == "By artist") {
                songsRepository.getSongs().sortedBy { it.artist }
            } else {
                songsRepository.getSongs().sortedBy { it.id }
            }
            stateMutable.update {
                it.copy(
                    songs = songs,
                    isLoading = false
                )
            }
        }
    }
}