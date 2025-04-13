package org.sound.hive.android.viewModel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.sound.hive.android.action.FavoritesAction
import org.sound.hive.android.data.repository.SongsRepository
import org.sound.hive.android.effect.FavoritesSideEffect
import org.sound.hive.android.effect.HistorySideEffect
import org.sound.hive.android.intent.FavoritesIntent
import org.sound.hive.android.ui.common.homeRoute
import org.sound.hive.android.viewModel.abstracts.BaseSongListScreenViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    songsRepository: SongsRepository
) : BaseSongListScreenViewModel<FavoritesIntent, FavoritesAction>(songsRepository) {

    private val sideEffectMutable = MutableSharedFlow<FavoritesSideEffect>()
    val sideEffect = sideEffectMutable.asSharedFlow()

    init {
        val action = processIntentInternal(FavoritesIntent.LoadInitialData)
        processAction(action)
    }

    override fun processIntentInternal(intent: FavoritesIntent): FavoritesAction {
        return when (intent) {
            is FavoritesIntent.LoadInitialData -> FavoritesAction.LoadInitialData
            is FavoritesIntent.NavigateBack -> FavoritesAction.NavigateBack
            is FavoritesIntent.UseFilters -> FavoritesAction.UseFilter("")
        }
    }

    override fun processAction(action: FavoritesAction) {
        when (action) {
            is FavoritesAction.LoadInitialData -> loadInitialData(FavoritesAction.LoadSongs)
            is FavoritesAction.NavigateBack -> navigate()
            is FavoritesAction.LoadSongs -> loadSongs()
            is FavoritesAction.UseFilter -> {}
        }
    }

    override fun navigate() {
        viewModelScope.launch {
            sideEffectMutable.emit(FavoritesSideEffect.NavigateBack(route = homeRoute))
        }
    }
}
