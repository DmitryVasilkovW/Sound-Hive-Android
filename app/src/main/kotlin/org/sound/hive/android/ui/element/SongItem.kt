package org.sound.hive.android.ui.element

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.layout.*
import androidx.compose.ui.res.*
import androidx.compose.ui.unit.*
import org.sound.hive.android.R
import org.sound.hive.android.model.*

@Composable
fun SongItem(song: Song, index: Int, onCoverClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 26.dp,
                end = 16.dp,
                top = 8.dp,
                bottom = 5.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$index",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
            modifier = Modifier.padding(end = 8.dp)
        )

        val imageRes = if (song.strTrackThumb != null) {
            R.drawable.velvet_underground_and_nico
        } else {
            R.drawable.ic_avatar_default
        }

        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Album cover",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(45.dp)
                .clip(RoundedCornerShape(8.dp))
                .clickable { onCoverClick() }
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = song.strTrack ?: "Unknown",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = song.strArtist ?: "Unknown",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )
        }
    }
}

val songsExample = mutableStateListOf(
    Song(
        idTrack = "1",
        strTrack = "Sunday Morning",
        strArtist = "The Velvet Underground"
    ),
    Song(
        idTrack = "2",
        strTrack = "Holland, 1945",
        strArtist = "Neutral Milk Hotel"
    ),
    Song(
        idTrack = "3",
        strTrack = "D>E>A>T>H>M>E>T>A>L",
        strArtist = "Panchiko"
    ),
    Song(
        idTrack = "4",
        strTrack = "Imperial",
        strArtist = "j^p^n"
    ),
    Song(
        idTrack = "5",
        strTrack = "Electrolux",
        strArtist = "Hoover"
    ),
    Song(
        idTrack = "6",
        strTrack = "Sober to Death",
        strArtist = "Car Seat Headrest"
    )
)
