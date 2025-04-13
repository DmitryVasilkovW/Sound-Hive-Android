package org.sound.hive.android.viewModel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.sound.hive.android.action.HistoryAction
import org.sound.hive.android.data.repository.SongsRepository
import org.sound.hive.android.effect.HistorySideEffect
import org.sound.hive.android.effect.HomeSideEffect
import org.sound.hive.android.intent.HistoryIntent
import org.sound.hive.android.ui.common.homeRoute
import org.sound.hive.android.viewModel.abstracts.BaseSongListScreenViewModel
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    songsRepository: SongsRepository
) : BaseSongListScreenViewModel<HistoryIntent, HistoryAction>(songsRepository) {

    private val sideEffectMutable = MutableSharedFlow<HistorySideEffect>()
    val sideEffect = sideEffectMutable.asSharedFlow()

    init {
        val action = processIntentInternal(HistoryIntent.LoadInitialData)
        processAction(action)
    }

    override fun processIntentInternal(intent: HistoryIntent): HistoryAction {
        return when (intent) {
            is HistoryIntent.LoadInitialData -> HistoryAction.LoadInitialData
            is HistoryIntent.NavigateBack -> HistoryAction.NavigateBack
            is HistoryIntent.UseFilters -> HistoryAction.UseFilter("")
        }
    }

    override fun processAction(action: HistoryAction) {
        when (action) {
            is HistoryAction.LoadInitialData -> loadInitialData(HistoryAction.LoadSongs)
            is HistoryAction.NavigateBack -> navigate()
            is HistoryAction.LoadSongs -> loadSongs()
            is HistoryAction.UseFilter -> {}
        }
    }

    override fun navigate() {
        viewModelScope.launch {
            sideEffectMutable.emit(HistorySideEffect.NavigateBack(route = homeRoute))
        }
    }
}
