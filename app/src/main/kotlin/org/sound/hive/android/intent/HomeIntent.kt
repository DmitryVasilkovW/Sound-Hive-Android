package org.sound.hive.android.intent

sealed class HomeIntent {
    object LoadInitialData : HomeIntent()
    object NavigateToAccount : HomeIntent()
    object NavigateToFavorites : HomeIntent()
    object NavigateToHistory : HomeIntent()
}
