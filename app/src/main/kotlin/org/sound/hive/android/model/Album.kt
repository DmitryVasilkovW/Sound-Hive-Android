package org.sound.hive.android.model

import kotlinx.serialization.*

@Serializable
data class Album(
    // Идентификатор альбома в базе данных API.
    @SerialName("idAlbum") val idAlbum: String,

    // Идентификатор исполнителя альбома.
    @SerialName("idArtist") val idArtist: String? = null,

    // Идентификатор лейбла, выпустившего альбом.
    @SerialName("idLabel") val idLabel: String? = null,

    // Название альбома.
    @SerialName("strAlbum") val title: String? = null,

    // Название альбома без дополнительных символов (чистая версия).
    @SerialName("strAlbumStripped") val strippedTitle: String? = null,

    // Имя исполнителя альбома.
    @SerialName("strArtist") val artist: String? = null,

    // Имя исполнителя без дополнительных символов (чистая версия).
    @SerialName("strArtistStripped") val strippedArtist: String? = null,

    // Год выпуска альбома (в виде строки).
    @SerialName("intYearReleased") val yearReleased: String? = null,

    // Стиль альбома (например, Urban/R&B).
    @SerialName("strStyle") val style: String? = null,

    // Жанр альбома (например, R&B).
    @SerialName("strGenre") val genre: String? = null,

    // Название лейбла звукозаписи.
    @SerialName("strLabel") val label: String? = null,

    // Формат выпуска (например, Mixtape/Street).
    @SerialName("strReleaseFormat") val releaseFormat: String? = null,

    // Количество продаж (в виде строки).
    @SerialName("intSales") val sales: String? = null,

    // Ссылка на миниатюру обложки альбома.
    @SerialName("strAlbumThumb") val thumbnail: String? = null,

    // Ссылка на высококачественную миниатюру альбома.
    @SerialName("strAlbumThumbHQ") val hqThumbnail: String? = null,

    // Ссылка на изображение задней стороны обложки.
    @SerialName("strAlbumBack") val backCover: String? = null,

    // Ссылка на CD-арт альбома.
    @SerialName("strAlbumCDart") val cdArt: String? = null,

    // Описание альбома на английском языке.
    @SerialName("strDescriptionEN") val descriptionEn: String? = null,

    // Описание альбома на немецком языке.
    @SerialName("strDescriptionDE") val descriptionDe: String? = null,

    // Описание альбома на французском языке.
    @SerialName("strDescriptionFR") val descriptionFr: String? = null,

    // Описание альбома на китайском языке.
    @SerialName("strDescriptionCN") val descriptionCn: String? = null,

    // Описание альбома на итальянском языке.
    @SerialName("strDescriptionIT") val descriptionIt: String? = null,

    // Описание альбома на японском языке.
    @SerialName("strDescriptionJP") val descriptionJp: String? = null,

    // Описание альбома на русском языке.
    @SerialName("strDescriptionRU") val descriptionRu: String? = null,

    // Описание альбома на испанском языке.
    @SerialName("strDescriptionES") val descriptionEs: String? = null,

    // Описание альбома на португальском языке.
    @SerialName("strDescriptionPT") val descriptionPt: String? = null,

    // Описание альбома на шведском языке.
    @SerialName("strDescriptionSE") val descriptionSe: String? = null,

    // Описание альбома на голландском языке.
    @SerialName("strDescriptionNL") val descriptionNl: String? = null,

    // Описание альбома на венгерском языке.
    @SerialName("strDescriptionHU") val descriptionHu: String? = null,

    // Описание альбома на норвежском языке.
    @SerialName("strDescriptionNO") val descriptionNo: String? = null,

    // Описание альбома на иврите.
    @SerialName("strDescriptionIL") val descriptionIl: String? = null,

    // Описание альбома на польском языке.
    @SerialName("strDescriptionPL") val descriptionPl: String? = null,

    // Количество отметок "нравится" для альбома.
    @SerialName("intLoved") val lovedCount: String? = null,

    // Оценка альбома (например, 8.5).
    @SerialName("intScore") val score: String? = null,

    // Количество голосов за оценку.
    @SerialName("intScoreVotes") val scoreVotes: String? = null,

    // Текст обзора альбома.
    @SerialName("strReview") val review: String? = null,

    // Настроение альбома (например, Intense).
    @SerialName("strMood") val mood: String? = null,

    // Тематика альбома.
    @SerialName("strTheme") val theme: String? = null,

    // Скорость музыки в альбоме (например, Medium).
    @SerialName("strSpeed") val speed: String? = null,

    // Место записи альбома.
    @SerialName("strLocation") val location: String? = null,

    // MusicBrainz ID альбома.
    @SerialName("strMusicBrainzID") val musicBrainzId: String? = null,

    // MusicBrainz ID исполнителя.
    @SerialName("strMusicBrainzArtistID") val musicBrainzArtistId: String? = null,

    // AllMusic ID альбома.
    @SerialName("strAllMusicID") val allMusicId: String? = null,

    // BBC Review ID.
    @SerialName("strBBCReviewID") val bbcReviewId: String? = null,

    // RateYourMusic ID.
    @SerialName("strRateYourMusicID") val rateYourMusicId: String? = null,

    // Discogs ID альбома.
    @SerialName("strDiscogsID") val discogsId: String? = null,

    // Wikidata ID альбома.
    @SerialName("strWikidataID") val wikidataId: String? = null,

    // Wikipedia ID альбома.
    @SerialName("strWikipediaID") val wikipediaId: String? = null,

    // Genius ID альбома.
    @SerialName("strGeniusID") val geniusId: String? = null,

    // LyricWiki ID альбома.
    @SerialName("strLyricWikiID") val lyricWikiId: String? = null,

    // MusicMoz ID альбома.
    @SerialName("strMusicMozID") val musicMozId: String? = null,

    // iTunes ID альбома.
    @SerialName("strItunesID") val iTunesId: String? = null,

    // Amazon ID альбома.
    @SerialName("strAmazonID") val amazonId: String? = null,

    // Статус блокировки альбома (например, unlocked).
    @SerialName("strLocked") val lockedStatus: String? = null
)
