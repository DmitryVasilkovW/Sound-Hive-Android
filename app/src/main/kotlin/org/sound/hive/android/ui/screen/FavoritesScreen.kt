package org.sound.hive.android.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.*
import androidx.compose.ui.tooling.preview.*
import androidx.navigation.*
import androidx.navigation.compose.*
import org.sound.hive.android.ui.common.*
import org.sound.hive.android.ui.element.*
import org.sound.hive.android.ui.theme.*
import org.sound.hive.android.R
import org.sound.hive.android.ui.sheets.DisketteModalSheet

@Composable
@Preview
fun FavoritesScreenPreview() {
    SoundHiveAndroid {
        FavoritesScreen(rememberNavController())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(navController: NavController) {
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
        title = stringResource(R.string.favorites_name),
        filterOptions = FilterOptions.historyFilters,
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
