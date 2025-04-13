package org.sound.hive.android.ui.element

import androidx.compose.material.icons.*
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.*
import org.sound.hive.android.intent.AccountIntent
import org.sound.hive.android.viewModel.AccountViewModel

@Composable
fun NavigationIcon(processNavigateBackIcon: () -> Unit) {
    IconButton(
        onClick = {
            processNavigateBackIcon()
        }
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Arrow Back",
        )
    }
}
