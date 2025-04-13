package org.sound.hive.android.effect

sealed class FavoritesSideEffect {
    data class NavigateBack(val route: String) : FavoritesSideEffect()
}
