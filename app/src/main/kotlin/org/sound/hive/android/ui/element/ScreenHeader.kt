package org.sound.hive.android.ui.element

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.res.*
import androidx.compose.ui.unit.*
import androidx.navigation.*

@Composable
fun ScreenHeaderWithSettings(
    navController: NavController,
    title: String,
    modifier: Modifier = Modifier
) {
    BaseScreenHeader(
        navController = navController,
        title = title,
        modifier = modifier
    ) {
        SettingsButton()
    }
}

@Composable
fun ScreenHeaderWithFilterMenu(
    navController: NavController,
    title: String,
    filterOptions: List<Int>,
    showFilterMenu: Boolean,
    onFilterMenuChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    BaseScreenHeader(
        navController = navController,
        title = title,
        modifier = modifier
    ) {
        FilterMenu(
            filterOptions = filterOptions,
            showMenu = showFilterMenu,
            onMenuChange = onFilterMenuChange,
        )
    }
}

@Composable
private fun BaseScreenHeader(
    navController: NavController,
    title: String,
    modifier: Modifier = Modifier,
    trailingContent: @Composable () -> Unit
) {
    Column(
        modifier = modifier.padding(
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
            ScreenTitle(title)
            trailingContent()
        }
    }
}

@Composable
private fun FilterMenu(
    filterOptions: List<Int>,
    showMenu: Boolean,
    onMenuChange: (Boolean) -> Unit
) {
    val filters = remember { filterOptions }

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
                .padding(vertical = 2.dp),
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
        modifier = Modifier.padding(horizontal = 6.dp),
        contentPadding = PaddingValues(vertical = 2.dp)
    )
    if (index < totalItems - 1) {
        MenuDivider()
    }
}

@Composable
private fun FilterMenuItemText(resId: Int) {
    Text(
        stringResource(resId),
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.padding(horizontal = 6.dp),
    )
}

@Composable
private fun MenuDivider() {
    HorizontalDivider(
        thickness = 0.5.dp,
        color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f),
        modifier = Modifier.padding(horizontal = 2.dp),
    )
}
