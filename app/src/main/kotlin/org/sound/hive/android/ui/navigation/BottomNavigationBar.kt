package org.sound.hive.android.ui.navigation

import androidx.compose.material.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.*
import androidx.compose.ui.tooling.preview.*
import androidx.navigation.*
import androidx.navigation.compose.*

@Composable
@Preview
fun BottomNavigationBarPreview() {
    BottomNavigationBar(rememberNavController())
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem("home", "Главная", Icons.Default.Home),
        BottomNavItem("details", "Детали", Icons.Default.Info)
    )

    BottomNavigation {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        items.forEach { (route, title, icon) ->
            BottomNavigationItem(
                icon = { androidx.compose.material3.Icon(icon, contentDescription = title) },
                label = { androidx.compose.material3.Text(title) },
                selected = currentRoute == route,
                onClick = {
                    if (currentRoute != route) {
                        navController.navigate(route) {
                            popUpTo("home") { inclusive = false }
                            launchSingleTop = true
                        }
                    }
                }
            )
        }
    }
}

data class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
)
