package org.sound.hive.android.model

import kotlinx.serialization.*

@Serializable
data class SongResponse(
    @SerialName("track") val track: List<Song>? = null
)
