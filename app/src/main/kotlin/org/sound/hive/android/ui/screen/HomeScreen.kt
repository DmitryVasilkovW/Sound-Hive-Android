package org.sound.hive.android.ui.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*

@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen()
}

@Composable
fun HomeScreen() {
    val trackList = friends

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Друзья", fontSize = 24.sp)

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(trackList) { track ->
                TrackItem(track)
            }
        }
    }
}

@Composable
fun TrackItem(track: Item) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                
            }
    ) {
        Text(text = track.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text(text = track.track, fontSize = 16.sp)
    }
}

data class Item(
    val name: String,
    val track: String
)

val friends = listOf(
    Item("King_kekov", "название трека"),
    Item("Dorima", "какой-то аниме опенинг"),
    Item("Bloom_guy", "название трека"),
    Item("CAXAP_c_MEDOM", "название трека"),
)
