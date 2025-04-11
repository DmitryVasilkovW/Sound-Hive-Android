package org.sound.hive.android.viewModel.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.sound.hive.android.action.FavoritesAction
import org.sound.hive.android.data.repository.SongsRepository
import org.sound.hive.android.effect.HomeSideEffect
import org.sound.hive.android.intent.FavoritesIntent
import org.sound.hive.android.model.Song
import org.sound.hive.android.model.favorites.FavoritesState
import org.sound.hive.android.ui.common.homeRoute
import org.sound.hive.android.viewModel.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val songsRepository: SongsRepository
) : ViewModel(), BaseViewModel {
    private val stateMutable = MutableStateFlow(FavoritesState())
    val state: StateFlow<FavoritesState> = stateMutable.asStateFlow()

    private val sideEffectMutable = MutableSharedFlow<HomeSideEffect>()

    init {
        processIntentUtilFunc(FavoritesIntent.LoadInitialData)
    }

    override suspend fun processIntent(intent: Any) {
        processIntentUtilFunc(intent as FavoritesIntent)
    }

    override fun getSongs(): List<Song> = songsRepository.getSongs()

    fun processIntentUtilFunc(intent: FavoritesIntent) {
        val action = when (intent) {
            is FavoritesIntent.LoadInitialData -> FavoritesAction.LoadInitialData
            is FavoritesIntent.NavigateToHome -> FavoritesAction.Navigate(homeRoute)
            is FavoritesIntent.UseFilters -> FavoritesAction.UseFilter("By Song")
        }

        processAction(action)
    }

    private fun processAction(action: FavoritesAction) {
        when (action) {
            is FavoritesAction.LoadInitialData -> loadInitialData()
            is FavoritesAction.Navigate -> navigate(action.route)
            is FavoritesAction.LoadSongs -> loadSongs()
            is FavoritesAction.UseFilter -> useFilters(action.filterType)
        }
    }


    private fun loadInitialData() {
        viewModelScope.launch {
            stateMutable.update { it.copy(isLoading = true) }
            processAction(FavoritesAction.LoadSongs)
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