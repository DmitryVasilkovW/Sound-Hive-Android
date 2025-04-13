package org.sound.hive.android.action

sealed class HistoryAction {
    object LoadInitialData : HistoryAction()
    object LoadSongs : HistoryAction()
    data class UseFilter(val filterType: String) : HistoryAction()
    object NavigateBack : HistoryAction()
}
