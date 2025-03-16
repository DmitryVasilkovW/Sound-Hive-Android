package org.sound.hive.android.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*
import androidx.navigation.*
import androidx.navigation.compose.*

@Composable
@Preview
fun AccountScreenPreview() {
    AccountScreen(rememberNavController())
}

@Composable
fun AccountScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Экран деталей", fontSize = 24.sp)
        Button(onClick = { navController.popBackStack() }) {
            Text(text = "Назад")
        }
    }
}
