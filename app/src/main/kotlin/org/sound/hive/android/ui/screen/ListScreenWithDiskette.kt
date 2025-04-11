package org.sound.hive.android.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.navigation.*
import org.sound.hive.android.ui.element.*
import org.sound.hive.android.ui.sheets.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreenWithDiskette(
    navController: NavController,
    title: String,
    filterOptions: List<Int>
) {
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
        title = title,
        filterOptions = filterOptions,

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
