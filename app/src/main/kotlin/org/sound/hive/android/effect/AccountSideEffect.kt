package org.sound.hive.android.effect

sealed class AccountSideEffect {
    data class NavigateBack(val route: String) : AccountSideEffect()
}
