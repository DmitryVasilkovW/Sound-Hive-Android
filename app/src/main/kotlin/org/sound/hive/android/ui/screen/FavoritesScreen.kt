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
import org.sound.hive.android.viewModel.favorites.FavoritesViewModel
import org.sound.hive.android.viewModel.home.HomeViewModel

@Composable
@Preview
fun FavoritesScreenPreview() {
    SoundHiveAndroid {
        FavoritesScreen(rememberNavController())
    }
}

@Composable
fun FavoritesScreen(
    navController: NavController,
    favoritesViewModel: FavoritesViewModel = hiltViewModel()
) {
    ListScreenWithDiskette(
        navController = navController,
        title = stringResource(R.string.favorites_name),
        filterOptions = FilterOptions.historyFilters,
        favoritesViewModel
    )
}
