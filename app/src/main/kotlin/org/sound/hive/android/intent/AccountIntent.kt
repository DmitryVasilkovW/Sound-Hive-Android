package org.sound.hive.android.intent

sealed class AccountIntent {
    object LoadInitialData : AccountIntent()
    object NavigateBack : AccountIntent()
}
