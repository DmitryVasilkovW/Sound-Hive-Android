package org.sound.hive.android.model

import org.sound.hive.android.*

data class User(
    val name: String = "Undefined",
    val email: String = "Undefined",
    val avatarId: Int = R.drawable.ic_avatar_default
)
