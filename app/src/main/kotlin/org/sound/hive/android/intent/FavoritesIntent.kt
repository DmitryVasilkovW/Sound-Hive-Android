package org.sound.hive.android.intent

sealed class FavoritesIntent {
    object LoadInitialData : FavoritesIntent()
    object UseFilters : FavoritesIntent()
    object NavigateToHome : FavoritesIntent()
}