package org.sound.hive.android.model.room.entity

import androidx.room.*

@Entity(tableName = "songs")
data class SongEntity(
    @PrimaryKey
    @ColumnInfo(name = "idTrack")
    val idTrack: String,

    @ColumnInfo(name = "idAlbum")
    val idAlbum: String? = null,

    @ColumnInfo(name = "idArtist")
    val idArtist: String? = null,

    @ColumnInfo(name = "idLyric")
    val idLyric: String? = null,

    @ColumnInfo(name = "idIMVDB")
    val idIMVDB: String? = null,

    @ColumnInfo(name = "strTrack")
    val strTrack: String? = null,

    @ColumnInfo(name = "strAlbum")
    val strAlbum: String? = null,

    @ColumnInfo(name = "strArtist")
    val strArtist: String? = null,

    @ColumnInfo(name = "strArtistAlternate")
    val strArtistAlternate: String? = null,

    @ColumnInfo(name = "intCD")
    val intCD: String? = null,

    @ColumnInfo(name = "intDuration")
    val intDuration: String? = null,

    @ColumnInfo(name = "strGenre")
    val strGenre: String? = null,

    @ColumnInfo(name = "strMood")
    val strMood: String? = null,

    @ColumnInfo(name = "strStyle")
    val strStyle: String? = null,

    @ColumnInfo(name = "strTheme")
    val strTheme: String? = null,

    @ColumnInfo(name = "strDescriptionEN")
    val strDescriptionEN: String? = null,

    @ColumnInfo(name = "strDescriptionDE")
    val strDescriptionDE: String? = null,

    @ColumnInfo(name = "strDescriptionFR")
    val strDescriptionFR: String? = null,

    @ColumnInfo(name = "strDescriptionCN")
    val strDescriptionCN: String? = null,

    @ColumnInfo(name = "strDescriptionIT")
    val strDescriptionIT: String? = null,

    @ColumnInfo(name = "strDescriptionJP")
    val strDescriptionJP: String? = null,

    @ColumnInfo(name = "strDescriptionRU")
    val strDescriptionRU: String? = null,

    @ColumnInfo(name = "strDescriptionES")
    val strDescriptionES: String? = null,

    @ColumnInfo(name = "strDescriptionPT")
    val strDescriptionPT: String? = null,

    @ColumnInfo(name = "strDescriptionSE")
    val strDescriptionSE: String? = null,

    @ColumnInfo(name = "strDescriptionNL")
    val strDescriptionNL: String? = null,

    @ColumnInfo(name = "strDescriptionHU")
    val strDescriptionHU: String? = null,

    @ColumnInfo(name = "strDescriptionNO")
    val strDescriptionNO: String? = null,

    @ColumnInfo(name = "strDescriptionIL")
    val strDescriptionIL: String? = null,

    @ColumnInfo(name = "strDescriptionPL")
    val strDescriptionPL: String? = null,

    @ColumnInfo(name = "strTrackThumb")
    val strTrackThumb: String? = null,

    @ColumnInfo(name = "strTrack3DCase")
    val strTrack3DCase: String? = null,

    @ColumnInfo(name = "strTrackLyrics")
    val strTrackLyrics: String? = null,

    @ColumnInfo(name = "strMusicVid")
    val strMusicVid: String? = null,

    @ColumnInfo(name = "strMusicVidDirector")
    val strMusicVidDirector: String? = null,

    @ColumnInfo(name = "strMusicVidCompany")
    val strMusicVidCompany: String? = null,

    @ColumnInfo(name = "strMusicVidScreen1")
    val strMusicVidScreen1: String? = null,

    @ColumnInfo(name = "strMusicVidScreen2")
    val strMusicVidScreen2: String? = null,

    @ColumnInfo(name = "strMusicVidScreen3")
    val strMusicVidScreen3: String? = null,

    @ColumnInfo(name = "intMusicVidViews")
    val intMusicVidViews: String? = null,

    @ColumnInfo(name = "intMusicVidLikes")
    val intMusicVidLikes: String? = null,

    @ColumnInfo(name = "intMusicVidDislikes")
    val intMusicVidDislikes: String? = null,

    @ColumnInfo(name = "intMusicVidFavorites")
    val intMusicVidFavorites: String? = null,

    @ColumnInfo(name = "intMusicVidComments")
    val intMusicVidComments: String? = null,

    @ColumnInfo(name = "intTrackNumber") val intTrackNumber: String? = null,

    @ColumnInfo(name = "intLoved")
    val intLoved: String? = null,

    @ColumnInfo(name = "intScore")
    val intScore: String? = null,

    @ColumnInfo(name = "intScoreVotes")
    val intScoreVotes: String? = null,

    @ColumnInfo(name = "intTotalListeners")
    val intTotalListeners: String? = null,

    @ColumnInfo(name = "intTotalPlays")
    val intTotalPlays: String? = null,

    @ColumnInfo(name = "strMusicBrainzID")
    val strMusicBrainzID: String? = null,

    @ColumnInfo(name = "strMusicBrainzAlbumID")
    val strMusicBrainzAlbumID: String? = null,

    @ColumnInfo(name = "strMusicBrainzArtistID")
    val strMusicBrainzArtistID: String? = null,

    @ColumnInfo(name = "strLocked")
    val strLocked: String? = null
)
