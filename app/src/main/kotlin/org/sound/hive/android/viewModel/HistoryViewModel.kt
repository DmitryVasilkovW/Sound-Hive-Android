package org.sound.hive.android.viewModel

import dagger.hilt.android.lifecycle.HiltViewModel
import org.sound.hive.android.action.HistoryAction
import org.sound.hive.android.data.repository.SongsRepository
import org.sound.hive.android.intent.HistoryIntent
import org.sound.hive.android.ui.common.homeRoute
import org.sound.hive.android.viewModel.abstracts.BaseSongListScreenViewModel
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    songsRepository: SongsRepository
) : BaseSongListScreenViewModel<HistoryIntent, HistoryAction>(songsRepository) {

    init {
        val action = processIntentInternal(HistoryIntent.LoadInitialData)
        processAction(action)
    }

    override fun processIntentInternal(intent: HistoryIntent): HistoryAction {
        return when (intent) {
            is HistoryIntent.LoadInitialData -> HistoryAction.LoadInitialData
            is HistoryIntent.NavigateToHome -> HistoryAction.Navigate(homeRoute)
            is HistoryIntent.UseFilters -> HistoryAction.UseFilter("")
        }
    }

    override fun processAction(action: HistoryAction) {
        when (action) {
            is HistoryAction.LoadInitialData -> loadInitialData(HistoryAction.LoadSongs)
            is HistoryAction.Navigate -> navigate(action.route)
            is HistoryAction.LoadSongs -> loadSongs()
            is HistoryAction.UseFilter -> {}
        }
    }
}
