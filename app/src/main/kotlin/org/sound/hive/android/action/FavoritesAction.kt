package org.sound.hive.android.action

sealed class FavoritesAction {
    object LoadInitialData : FavoritesAction()
    object LoadSongs : FavoritesAction()
    data class UseFilter(val filterType: String) : FavoritesAction()
    data class Navigate(val route: String) : FavoritesAction()
}