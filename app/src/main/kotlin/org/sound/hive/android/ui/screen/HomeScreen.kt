package org.sound.hive.android.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.*
import androidx.navigation.*

@Composable
fun HomeScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Главный экран", fontSize = 24.sp)
        Button(onClick = { navController.navigate("details") }) {
            Text(text = "Перейти на детали")
        }
    }
}