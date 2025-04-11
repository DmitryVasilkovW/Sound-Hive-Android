package org.sound.hive.android.action

sealed class AccountAction {
    object LoadUser : AccountAction()
    object LoadInitialData : AccountAction()
    data class Navigate(val route: String) : AccountAction()
}
