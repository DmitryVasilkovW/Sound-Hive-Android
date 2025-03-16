package org.sound.hive.android

import android.os.*
import androidx.activity.*
import androidx.activity.compose.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.tooling.preview.*
import androidx.navigation.compose.*
import org.sound.hive.android.ui.common.*
import org.sound.hive.android.ui.navigation.*
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

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = HOME,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(HOME) {
                HomeScreen(navController)
            }

            composable(ACCOUNT) {
                AccountScreen(navController)
            }

            composable("details/{id}") { backStackEntry ->
                val id = backStackEntry.arguments?.getString("id")
                id?.let {
                    TrackDetailsScreen(trackId = it)
                }
            }
        }
    }
}
