package org.sound.hive.android.ui.element

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.sound.hive.android.R
import org.sound.hive.android.model.Song

@Composable
fun SongItem(song: Song, index: Int) {
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

        Image(
            painter = painterResource(id = song.coverResId),
            contentDescription = "Album cover",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(45.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = song.title,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = song.artist,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
            )
        }
    }
}

val songsExample = listOf(
    Song(
        id = 1,
        coverResId = R.drawable.velvet_underground_and_nico,
        title = "Sunday Morning",
        artist = "The Velvet Underground"
    ),
    Song(
        id = 2,
        coverResId = R.drawable.neutral_milk_hotel,
        title = "Holland, 1945",
        artist = "Neutral Milk Hotel"
    ),
    Song(
        id = 3,
        coverResId = R.drawable.panchiko,
        title = "D>E>A>T>H>M>E>T>A>L",
        artist = "Panchiko"
    ),
    Song(
        id = 4,
        coverResId = R.drawable.jpn,
        title = "Imperial",
        artist = "j^p^n"
    ),
    Song(
        id = 5,
        coverResId = R.drawable.hoover,
        title = "Electrolux",
        artist = "Hoover"
    ),
    Song(
        id = 5,
        coverResId = R.drawable.car_seat_headrest,
        title = "Sober to Death",
        artist = "Car Seat Headrest"
    )
)