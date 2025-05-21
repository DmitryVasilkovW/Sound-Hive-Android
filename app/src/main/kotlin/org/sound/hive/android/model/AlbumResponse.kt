package org.sound.hive.android.model

import kotlinx.serialization.*

@Serializable
data class AlbumResponse(
    @SerialName("album")
    val album: List<Album>? = null
)
