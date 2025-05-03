package org.sound.hive.android.model

import kotlinx.serialization.*

@Serializable
data class Song(
    // Идентификатор трека в базе данных API.
    @SerialName("idTrack") val idTrack: String,

    // Идентификатор альбома, к которому относится трек.
    @SerialName("idAlbum") val idAlbum: String? = null,

    // Идентификатор артиста, который исполнил этот трек.
    @SerialName("idArtist") val idArtist: String? = null,

    // Идентификатор текста песни (если он существует).
    @SerialName("idLyric") val idLyric: String? = null,

    // Идентификатор IMVDB (если существует), может быть связан с музыкальными видео.
    @SerialName("idIMVDB") val idIMVDB: String? = null,

    // Название трека.
    @SerialName("strTrack") val strTrack: String? = null,

    // Название альбома, к которому относится трек.
    @SerialName("strAlbum") val strAlbum: String? = null,

    // Имя исполнителя.
    @SerialName("strArtist") val strArtist: String? = null,

    // Альтернативное имя исполнителя (если оно существует).
    @SerialName("strArtistAlternate") val strArtistAlternate: String? = null,

    // Порядковый номер CD (если трек входит в альбом с несколькими дисками).
    @SerialName("intCD") val intCD: String? = null,

    // Длительность трека в миллисекундах.
    @SerialName("intDuration") val intDuration: String? = null,

    // Жанр трека.
    @SerialName("strGenre") val strGenre: String? = null,

    // Настроение трека.
    @SerialName("strMood") val strMood: String? = null,

    // Стиль трека.
    @SerialName("strStyle") val strStyle: String? = null,

    // Тематика трека.
    @SerialName("strTheme") val strTheme: String? = null,

    // Описание трека на английском языке.
    @SerialName("strDescriptionEN") val strDescriptionEN: String? = null,

    // Описание трека на немецком языке.
    @SerialName("strDescriptionDE") val strDescriptionDE: String? = null,

    // Описание трека на французском языке.
    @SerialName("strDescriptionFR") val strDescriptionFR: String? = null,

    // Описание трека на китайском языке.
    @SerialName("strDescriptionCN") val strDescriptionCN: String? = null,

    // Описание трека на итальянском языке.
    @SerialName("strDescriptionIT") val strDescriptionIT: String? = null,

    // Описание трека на японском языке.
    @SerialName("strDescriptionJP") val strDescriptionJP: String? = null,

    // Описание трека на русском языке.
    @SerialName("strDescriptionRU") val strDescriptionRU: String? = null,

    // Описание трека на испанском языке.
    @SerialName("strDescriptionES") val strDescriptionES: String? = null,

    // Описание трека на португальском языке.
    @SerialName("strDescriptionPT") val strDescriptionPT: String? = null,

    // Описание трека на шведском языке.
    @SerialName("strDescriptionSE") val strDescriptionSE: String? = null,

    // Описание трека на голландском языке.
    @SerialName("strDescriptionNL") val strDescriptionNL: String? = null,

    // Описание трека на венгерском языке.
    @SerialName("strDescriptionHU") val strDescriptionHU: String? = null,

    // Описание трека на норвежском языке.
    @SerialName("strDescriptionNO") val strDescriptionNO: String? = null,

    // Описание трека на иврите.
    @SerialName("strDescriptionIL") val strDescriptionIL: String? = null,

    // Описание трека на польском языке.
    @SerialName("strDescriptionPL") val strDescriptionPL: String? = null,

    // Миниатюра изображения трека (если существует).
    @SerialName("strTrackThumb") val strTrackThumb: String? = null,

    // 3D изображение упаковки для трека (если существует).
    @SerialName("strTrack3DCase") val strTrack3DCase: String? = null,

    // Лирика трека.
    @SerialName("strTrackLyrics") val strTrackLyrics: String? = null,

    // Ссылка на музыкальное видео (если существует).
    @SerialName("strMusicVid") val strMusicVid: String? = null,

    // Режиссер музыкального видео.
    @SerialName("strMusicVidDirector") val strMusicVidDirector: String? = null,

    // Компания, которая выпустила музыкальное видео.
    @SerialName("strMusicVidCompany") val strMusicVidCompany: String? = null,

    // Ссылка на первый экран музыкального видео.
    @SerialName("strMusicVidScreen1") val strMusicVidScreen1: String? = null,

    // Ссылка на второй экран музыкального видео.
    @SerialName("strMusicVidScreen2") val strMusicVidScreen2: String? = null,

    // Ссылка на третий экран музыкального видео.
    @SerialName("strMusicVidScreen3") val strMusicVidScreen3: String? = null,

    // Количество просмотров музыкального видео.
    @SerialName("intMusicVidViews") val intMusicVidViews: String? = null,

    // Количество лайков музыкального видео.
    @SerialName("intMusicVidLikes") val intMusicVidLikes: String? = null,

    // Количество дизлайков музыкального видео.
    @SerialName("intMusicVidDislikes") val intMusicVidDislikes: String? = null,

    // Количество избранных музыкальных видео.
    @SerialName("intMusicVidFavorites") val intMusicVidFavorites: String? = null,

    // Количество комментариев к музыкальному видео.
    @SerialName("intMusicVidComments") val intMusicVidComments: String? = null,

    // Номер трека на альбоме.
    @SerialName("intTrackNumber") val intTrackNumber: String? = null,

    // Показатель любви к треку (обычно оценка).
    @SerialName("intLoved") val intLoved: String? = null,

    // Оценка трека.
    @SerialName("intScore") val intScore: String? = null,

    // Количество голосов за трек.
    @SerialName("intScoreVotes") val intScoreVotes: String? = null,

    // Общее количество слушателей трека.
    @SerialName("intTotalListeners") val intTotalListeners: String? = null,

    // Общее количество проигрываний трека.
    @SerialName("intTotalPlays") val intTotalPlays: String? = null,

    // MusicBrainz ID трека.
    @SerialName("strMusicBrainzID") val strMusicBrainzID: String? = null,

    // MusicBrainz ID альбома.
    @SerialName("strMusicBrainzAlbumID") val strMusicBrainzAlbumID: String? = null,

    // MusicBrainz ID артиста.
    @SerialName("strMusicBrainzArtistID") val strMusicBrainzArtistID: String? = null,

    // Статус блокировки трека (если он заблокирован).
    @SerialName("strLocked") val strLocked: String? = null
)