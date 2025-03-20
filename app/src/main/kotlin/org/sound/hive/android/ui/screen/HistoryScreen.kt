package org.sound.hive.android.ui.screen

import androidx.compose.runtime.*
import androidx.compose.ui.res.*
import androidx.compose.ui.tooling.preview.*
import androidx.navigation.*
import androidx.navigation.compose.*
import org.sound.hive.android.R
import org.sound.hive.android.ui.common.*
import org.sound.hive.android.ui.element.*
import org.sound.hive.android.ui.theme.*

@Composable
@Preview
fun HistoryScreenPreview() {
    SoundHiveAndroid {
        HistoryScreen(rememberNavController())
    }
}

@Composable
fun HistoryScreen(navController: NavController) {
    CommonListScreen(
        navController = navController,
        title = stringResource(R.string.history_name),
        filterOptions = FilterOptions.favoritesFilters,
    ) {
        SongList()
    }
}
