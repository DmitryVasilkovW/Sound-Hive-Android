package org.sound.hive.android.ui.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import org.sound.hive.android.ui.element.*
import org.sound.hive.android.ui.theme.*

@Composable
fun CommonListScreen(
    title: String,
    filterOptions: List<Int>,
    processNavigateBackIcon: () -> Unit,
    content: @Composable () -> Unit,
) {
    var showFilterMenu by remember { mutableStateOf(false) }

    SoundHiveAndroid {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            ScreenHeaderWithFilterMenu(
                title = title,
                filterOptions = filterOptions,
                showFilterMenu = showFilterMenu,
                onFilterMenuChange = { showFilterMenu = it },
                processNavigateBackIcon = { processNavigateBackIcon() }
            )

            content()
        }
    }
}
