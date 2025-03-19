package org.sound.hive.android.model

import androidx.annotation.DrawableRes

data class Song(
    val id: Int,
    @DrawableRes val coverResId: Int,
    val title: String,
    val artist: String
)