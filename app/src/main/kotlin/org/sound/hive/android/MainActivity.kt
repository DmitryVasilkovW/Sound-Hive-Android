package org.sound.hive.android

import android.os.*
import androidx.activity.*
import androidx.activity.compose.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.*
import androidx.navigation.compose.*
import org.sound.hive.android.ui.common.*
import org.sound.hive.android.ui.screen.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavHost()
        }
    }
}

@Composable
@Preview
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = homeRoute,
    ) {
        composable(homeRoute) {
            HomeScreen(navController)
        }

        composable(accountRoute) {
            AccountScreen(navController)
        }

        composable(historyRoute) {
            HistoryScreen(navController)
        }

        composable(favoritesRoute) {
            FavoritesScreen(navController)
        }
    }
}
