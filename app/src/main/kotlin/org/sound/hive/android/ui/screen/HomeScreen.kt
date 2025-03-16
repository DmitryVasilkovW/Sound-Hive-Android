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
import androidx.navigation.*
import androidx.navigation.compose.*

@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}

@Composable
fun HomeScreen(navController: NavController) {
    val trackList = friends

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Друзья", fontSize = 24.sp)

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(trackList) { track ->
                TrackItem(track, navController)
            }
        }
    }
}

@Composable
fun TrackItem(track: Item, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                navController.navigate("details/${track.id}")
            }
    ) {
        Text(text = track.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text(text = track.track, fontSize = 16.sp)
    }
}


data class Item(
    val id: String,
    val name: String,
    val track: String,
)

val friends = listOf(
    Item("1", "King_kekov", "название трека"),
    Item("2", "Dorima", "какой-то аниме опенинг"),
    Item("3", "Bloom_guy", "название трека"),
    Item("4", "CAXAP_c_MEDOM", "название трека"),
)
