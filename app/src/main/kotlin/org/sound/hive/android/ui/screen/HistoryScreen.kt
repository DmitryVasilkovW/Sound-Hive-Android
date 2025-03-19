package org.sound.hive.android.ui.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.res.*
import androidx.compose.ui.tooling.preview.*
import androidx.navigation.*
import androidx.navigation.compose.*
import org.sound.hive.android.ui.element.*
import org.sound.hive.android.ui.theme.*
import org.sound.hive.android.R

@Composable
@Preview
fun HistoryScreenPreview() {
    SoundHiveAndroid {
        HistoryScreen(rememberNavController())
    }
}

@Composable
fun HistoryScreen(navController: NavController) {
    var showFilterMenu by remember { mutableStateOf(false) }

    SoundHiveAndroid {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
        ) {
            ScreenHeaderWithFilterMenu(
                navController,
                stringResource(R.string.history_name),
                FilterHistoryOptions.list,
                showFilterMenu,
            ) {
                showFilterMenu = it
            }

            SongList()
        }
    }
}

private object FilterHistoryOptions {
    val list = listOf(
        R.string.last_listened_filter,
        R.string.top_month_filter,
        R.string.top_year_filter,
        R.string.top_all_time_filter
    )
}