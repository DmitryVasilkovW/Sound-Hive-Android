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
import org.sound.hive.android.viewModel.AccountViewModel
import kotlin.Unit

@Composable
fun ScreenHeaderWithSettings(
    title: String,
    modifier: Modifier = Modifier,
    processNavigateBackIcon: () -> Unit,
) {
    BaseScreenHeader(
        title = title,
        modifier = modifier,
        trailingContent = { SettingsButton() },
        processNavigateBackIcon = { processNavigateBackIcon() }
    )
}

@Composable
fun ScreenHeaderWithFilterMenu(
    title: String,
    filterOptions: List<Int>,
    showFilterMenu: Boolean,
    modifier: Modifier = Modifier,
    onFilterMenuChange: (Boolean) -> Unit,
    processNavigateBackIcon: () -> Unit,
) {
    BaseScreenHeader(
        title = title,
        modifier = modifier,
        processNavigateBackIcon = { processNavigateBackIcon() }
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
    title: String,
    modifier: Modifier = Modifier,
    processNavigateBackIcon: () -> Unit,
    trailingContent: @Composable () -> Unit,
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
            NavigationIcon {
                processNavigateBackIcon()
            }
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
