package org.sound.hive.android.effect

sealed class HistorySideEffect {
    data class NavigateBack(val route: String) : HistorySideEffect()
}
