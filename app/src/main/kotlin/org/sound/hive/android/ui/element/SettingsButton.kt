package org.sound.hive.android.ui.element

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.*
import org.sound.hive.android.R

@Composable
fun SettingsButton() {
    IconButton(onClick = {}) {
        Icon(
            painter = painterResource(R.drawable.ic_settings),
            contentDescription = "Settings",
            tint = MaterialTheme.colorScheme.onBackground,
        )
    }
}
