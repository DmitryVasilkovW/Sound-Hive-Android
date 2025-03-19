package org.sound.hive.android.ui.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.res.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*
import androidx.navigation.*
import androidx.navigation.compose.*
import org.sound.hive.android.ui.theme.*
import org.sound.hive.android.R
import org.sound.hive.android.ui.element.NavigationIcon
import org.sound.hive.android.ui.element.ScreenTitle
import org.sound.hive.android.ui.element.SongItem
import org.sound.hive.android.ui.element.songsExample

@Composable
@Preview
fun HistoryScreenPreview() {
    SoundHiveAndroid {
        HistoryScreen(rememberNavController())
    }
}

@Composable
fun HistoryScreen(navController: NavController) {
    var showFilterMenu by remember { mutableStateOf(false) }

    SoundHiveAndroid {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
        ) {
            HistoryHeader(navController, showFilterMenu) { showFilterMenu = it }
            SongList()
        }
    }
}

@Composable
private fun HistoryHeader(
    navController: NavController,
    showFilterMenu: Boolean,
    onFilterMenuChange: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier.padding(
            top = 16.dp,
            bottom = 6.dp,
            start = 16.dp,
            end = 16.dp,
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            NavigationIcon(navController)
            ScreenTitle(stringResource(R.string.history_name))
            FilterMenu(showFilterMenu, onFilterMenuChange)
        }
    }
}

@Composable
private fun FilterMenu(showMenu: Boolean, onMenuChange: (Boolean) -> Unit) {
    val filters = remember { FilterOptions.list }

    Box {
        IconButton(onClick = { onMenuChange(!showMenu) }) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Filters Arrow",
            )
        }

        DropdownMenu(
            expanded = showMenu,
            onDismissRequest = { onMenuChange(false) },
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .dropdownMenuModifier(),
        ) {
            filters.forEachIndexed { index, resId ->
                FilterMenuItem(resId, index, filters.size)
            }
        }
    }
}

@Composable
private fun FilterMenuItem(resId: Int, index: Int, totalItems: Int) {
    DropdownMenuItem(
        text = { FilterMenuItemText(resId) },
        onClick = { },
        modifier = Modifier.menuItemPadding(),
        contentPadding = menuItemContentPadding()
    )

    if (shouldAddDivider(index, totalItems)) {
        MenuDivider()
    }
}

@Composable
private fun FilterMenuItemText(resId: Int) {
    Text(
        stringResource(resId),
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.textPadding(),
    )
}

@Composable
private fun MenuDivider() {
    HorizontalDivider(
        thickness = 0.5.dp,
        color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f),
        modifier = Modifier.dividerPadding(),
    )
}

@Composable
private fun SongList() {
    val songs = remember { songsExample }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(songs) { index, song ->
            SongItem(song = song, index = index + 1)
        }
    }
}

private fun Modifier.dropdownMenuModifier() = this.padding(vertical = 2.dp)

private fun Modifier.menuItemPadding() = this.padding(horizontal = 6.dp)

private fun Modifier.textPadding() = this.padding(horizontal = 6.dp)

private fun Modifier.dividerPadding() = this.padding(horizontal = 2.dp)

private fun menuItemContentPadding() = PaddingValues(vertical = 2.dp)

private fun shouldAddDivider(index: Int, total: Int) = index < total - 1

private object FilterOptions {
    val list = listOf(
        R.string.last_listened_filter,
        R.string.top_month_filter,
        R.string.top_year_filter,
        R.string.top_all_time_filter
    )
}