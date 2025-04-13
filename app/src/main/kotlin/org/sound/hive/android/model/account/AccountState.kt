package org.sound.hive.android.model.account

import org.sound.hive.android.model.*

data class AccountState(
    val user: User = User(),
    val isLoading: Boolean = false
)
