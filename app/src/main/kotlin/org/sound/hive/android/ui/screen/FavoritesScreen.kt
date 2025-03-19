package org.sound.hive.android.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.sound.hive.android.R
import org.sound.hive.android.ui.element.ScreenHeaderWithFilterMenu
import org.sound.hive.android.ui.element.SongList
import org.sound.hive.android.ui.theme.SoundHiveAndroid

@Composable
@Preview
fun FavoritesScreenPreview() {
    SoundHiveAndroid {
        FavoritesScreen(rememberNavController())
    }
}

@Composable
fun FavoritesScreen(navController: NavController) {
    var showFilterMenu by remember { mutableStateOf(false) }

    SoundHiveAndroid {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
        ) {
            ScreenHeaderWithFilterMenu(
                navController,
                stringResource(R.string.favorites_name),
                FilterFavoritesOptions.list,
                showFilterMenu,
            ) {
                showFilterMenu = it
            }

            SongList()
        }
    }
}

private object FilterFavoritesOptions {
    val list = listOf(
        R.string.song_title_filter,
        R.string.artist_name_filter,
        R.string.album_title_filter,
        R.string.add_date_filter,
    )
}