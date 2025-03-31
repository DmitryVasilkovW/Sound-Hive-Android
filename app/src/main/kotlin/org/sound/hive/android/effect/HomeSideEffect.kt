package org.sound.hive.android.effect

sealed class HomeSideEffect {
    data class Navigate(val route: String) : HomeSideEffect()
}
