package org.sound.hive.android.ui.screen

import androidx.compose.runtime.*
import androidx.compose.ui.res.*
import androidx.compose.ui.tooling.preview.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.*
import org.sound.hive.android.R
import org.sound.hive.android.ui.common.*
import org.sound.hive.android.ui.theme.*
import org.sound.hive.android.viewModel.HistoryViewModel

@Composable
@Preview
fun HistoryScreenPreview() {
    SoundHiveAndroid {
        HistoryScreen(rememberNavController())
    }
}

@Composable
fun HistoryScreen(
    navController: NavController,
    historyViewModel: HistoryViewModel = hiltViewModel()
) {
    ListScreenWithDiskette(
        navController = navController,
        title = stringResource(R.string.history_name),
        filterOptions = FilterOptions.favoritesFilters,
        historyViewModel
    )
}
