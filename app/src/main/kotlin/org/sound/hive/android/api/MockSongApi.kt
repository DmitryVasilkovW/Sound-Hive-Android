package org.sound.hive.android.api

import org.sound.hive.android.model.Song
import javax.inject.Inject

class MockSongApi @Inject constructor() : SongApi {

    override suspend fun getSongsByAlbum(albumId: String): List<Song> {
        return mockSongs
    }

    override suspend fun getSongById(songId: String): Song? {
        return mockSongs.find { it.idTrack == songId }
    }

    override suspend fun getSongByMusicBrainzId(mbId: String): Song? {
        return mockSongs.find { it.strMusicBrainzID == mbId }
    }

    companion object {
        private val mockSongs = listOf(
            Song(
                idTrack = "1001",
                idAlbum = "2001",
                idArtist = "3001",
                strTrack = "Bohemian Rhapsody",
                strAlbum = "A Night at the Opera",
                strArtist = "Queen",
                intDuration = "354000",
                strGenre = "Rock",
                strTrackThumb = "https://www.theaudiodb.com/images/media/track/thumb/bohemian-rhapsody-4f7b18e9e81c7.jpg"
            ),
            Song(
                idTrack = "1002",
                idAlbum = "2001",
                idArtist = "3001",
                strTrack = "Love of My Life",
                strAlbum = "A Night at the Opera",
                strArtist = "Queen",
                intDuration = "219000",
                strGenre = "Rock",
                strTrackThumb = "https://www.theaudiodb.com/images/media/track/thumb/love-of-my-life-4f7b18e9e81c7.jpg"
            ),
            Song(
                idTrack = "1003",
                idAlbum = "2002",
                idArtist = "3002",
                strTrack = "Billie Jean",
                strAlbum = "Thriller",
                strArtist = "Michael Jackson",
                intDuration = "294000",
                strGenre = "Pop",
                strTrackThumb = "https://www.theaudiodb.com/images/media/track/thumb/billie-jean-4f7b18e9e81c7.jpg"
            ),
            Song(
                idTrack = "1004",
                idAlbum = "2002",
                idArtist = "3002",
                strTrack = "Beat It",
                strAlbum = "Thriller",
                strArtist = "Michael Jackson",
                intDuration = "258000",
                strGenre = "Pop",
                strTrackThumb = "https://www.theaudiodb.com/images/media/track/thumb/beat-it-4f7b18e9e81c7.jpg"
            ),
            Song(
                idTrack = "1005",
                idAlbum = "2003",
                idArtist = "3003",
                strTrack = "Yesterday",
                strAlbum = "Help!",
                strArtist = "The Beatles",
                intDuration = "125000",
                strGenre = "Rock",
                strMusicBrainzID = "mb-1005",
                strTrackThumb = "https://www.theaudiodb.com/images/media/track/thumb/yesterday-4f7b18e9e81c7.jpg"
            ),
            Song(
                idTrack = "1006",
                idAlbum = "2003",
                idArtist = "3003",
                strTrack = "Help!",
                strAlbum = "Help!",
                strArtist = "The Beatles",
                intDuration = "139000",
                strGenre = "Rock",
                strMusicBrainzID = "mb-1006",
                strTrackThumb = "https://www.theaudiodb.com/images/media/track/thumb/help-4f7b18e9e81c7.jpg"
            ),
            Song(
                idTrack = "1007",
                idAlbum = "2004",
                idArtist = "3004",
                strTrack = "Smells Like Teen Spirit",
                strAlbum = "Nevermind",
                strArtist = "Nirvana",
                intDuration = "301000",
                strGenre = "Grunge",
                strMusicBrainzID = "mb-1007",
                strTrackThumb = "https://www.theaudiodb.com/images/media/track/thumb/smells-like-teen-spirit-4f7b18e9e81c7.jpg"
            ),
            Song(
                idTrack = "1008",
                idAlbum = "2004",
                idArtist = "3004",
                strTrack = "Come As You Are",
                strAlbum = "Nevermind",
                strArtist = "Nirvana",
                intDuration = "219000",
                strGenre = "Grunge",
                strMusicBrainzID = "mb-1008",
                strTrackThumb = "https://www.theaudiodb.com/images/media/track/thumb/come-as-you-are-4f7b18e9e81c7.jpg"
            ),
            Song(
                idTrack = "1009",
                idAlbum = "2005",
                idArtist = "3005",
                strTrack = "Stairway to Heaven",
                strAlbum = "Led Zeppelin IV",
                strArtist = "Led Zeppelin",
                intDuration = "482000",
                strGenre = "Rock",
                strMusicBrainzID = "mb-1009",
                strTrackThumb = "https://www.theaudiodb.com/images/media/track/thumb/stairway-to-heaven-4f7b18e9e81c7.jpg"
            ),
            Song(
                idTrack = "1010",
                idAlbum = "2005",
                idArtist = "3005",
                strTrack = "Black Dog",
                strAlbum = "Led Zeppelin IV",
                strArtist = "Led Zeppelin",
                intDuration = "294000",
                strGenre = "Rock",
                strMusicBrainzID = "mb-1010",
                strTrackThumb = "https://www.theaudiodb.com/images/media/track/thumb/black-dog-4f7b18e9e81c7.jpg"
            )
        )
    }
}