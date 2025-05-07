package org.sound.hive.android.model

import kotlinx.serialization.Serializable

@Serializable
data class SongResponse(
    val song: List<Song>?
)