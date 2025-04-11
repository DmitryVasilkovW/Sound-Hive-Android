package org.sound.hive.android.effect

sealed class AccountSideEffect {
    data class Navigate(val route: String) : AccountSideEffect()
}
