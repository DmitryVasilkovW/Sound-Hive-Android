package org.sound.hive.android.ui.screen

import androidx.compose.runtime.*
import androidx.compose.ui.res.*
import androidx.compose.ui.tooling.preview.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.*
import org.sound.hive.android.R
import org.sound.hive.android.effect.HistorySideEffect
import org.sound.hive.android.intent.HistoryIntent
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
    viewModel: HistoryViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is HistorySideEffect.NavigateBack -> {
                    navController.navigate(sideEffect.route)
                }
            }
        }
    }

    ListScreenWithDiskette(
        title = stringResource(R.string.history_name),
        filterOptions = FilterOptions.favoritesFilters,
        viewModel = viewModel
    ) {
        viewModel.processIntent(HistoryIntent.NavigateBack)
    }
}
