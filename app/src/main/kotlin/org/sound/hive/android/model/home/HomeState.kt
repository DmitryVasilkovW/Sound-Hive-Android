package org.sound.hive.android.model.home

import org.sound.hive.android.R
import org.sound.hive.android.model.Friend

data class HomeState(
    val friends: List<Friend> = emptyList(),
    val albumCoverResId: Int = R.drawable.ic_loading_disc_vinyl,
    val isLoading: Boolean = false
)
