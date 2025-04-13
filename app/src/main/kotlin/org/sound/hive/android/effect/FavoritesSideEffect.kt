package org.sound.hive.android.effect

sealed class FavoritesSideEffect {
    data class Navigate(val route: String) : FavoritesSideEffect()
}