package org.sound.hive.android.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.sound.hive.android.R
import org.sound.hive.android.ui.common.FilterOptions
import org.sound.hive.android.ui.element.SongList
import org.sound.hive.android.ui.sheets.DisketteModalSheet
import org.sound.hive.android.ui.theme.SoundHiveAndroid
import androidx.compose.material3.rememberModalBottomSheetState

@Composable
@Preview
fun HistoryScreenPreview() {
    SoundHiveAndroid {
        HistoryScreen(rememberNavController())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(navController: NavController) {
    var showDiskette by remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState()
    LaunchedEffect(showDiskette) {
        if (showDiskette) {
            sheetState.show()
        } else {
            sheetState.hide()
        }
    }

    CommonListScreen(
        navController = navController,
        title = stringResource(R.string.history_name),
        filterOptions = FilterOptions.favoritesFilters,
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            SongList(onSongClick = { showDiskette = true })
        }
    }

    DisketteModalSheet(
        showSheet = showDiskette,
        onDismiss = { showDiskette = false },
        sheetState = sheetState
    )
}
