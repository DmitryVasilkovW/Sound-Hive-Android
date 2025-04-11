package org.sound.hive.android.effect

sealed class HistorySideEffect {
    data class Navigate(val route: String) : HistorySideEffect()
}