package org.sound.hive.android.viewModel

import dagger.hilt.android.lifecycle.HiltViewModel
import org.sound.hive.android.action.FavoritesAction
import org.sound.hive.android.data.repository.SongsRepository
import org.sound.hive.android.intent.FavoritesIntent
import org.sound.hive.android.ui.common.homeRoute
import org.sound.hive.android.viewModel.abstracts.BaseSongListScreenViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    songsRepository: SongsRepository
) : BaseSongListScreenViewModel<FavoritesIntent, FavoritesAction>(songsRepository) {

    init {
        val action = processIntentInternal(FavoritesIntent.LoadInitialData)
        processAction(action)
    }

    override fun processIntentInternal(intent: FavoritesIntent): FavoritesAction {
        return when (intent) {
            is FavoritesIntent.LoadInitialData -> FavoritesAction.LoadInitialData
            is FavoritesIntent.NavigateToHome -> FavoritesAction.Navigate(homeRoute)
            is FavoritesIntent.UseFilters -> FavoritesAction.UseFilter("")
        }
    }

    override fun processAction(action: FavoritesAction) {
        when (action) {
            is FavoritesAction.LoadInitialData -> loadInitialData(FavoritesAction.LoadSongs)
            is FavoritesAction.Navigate -> navigate(action.route)
            is FavoritesAction.LoadSongs -> loadSongs()
            is FavoritesAction.UseFilter -> {}
        }
    }
}
