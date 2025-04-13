package org.sound.hive.android.intent

sealed class HistoryIntent {
    object LoadInitialData : HistoryIntent()
    object UseFilters : HistoryIntent()
    object NavigateBack : HistoryIntent()
}
