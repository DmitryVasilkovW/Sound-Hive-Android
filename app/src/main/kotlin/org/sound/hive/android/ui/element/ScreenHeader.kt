package org.sound.hive.android.ui.element

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ScreenHeaderWithFilterMenu(
    navController: NavController,
    label: String,
    filterOptions: List<Int>,
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
            ScreenTitle(label)
            FilterMenu(filterOptions, showFilterMenu, onFilterMenuChange)
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

private fun Modifier.dropdownMenuModifier() = this.padding(vertical = 2.dp)

private fun Modifier.menuItemPadding() = this.padding(horizontal = 6.dp)

private fun Modifier.textPadding() = this.padding(horizontal = 6.dp)

private fun Modifier.dividerPadding() = this.padding(horizontal = 2.dp)

private fun menuItemContentPadding() = PaddingValues(vertical = 2.dp)

private fun shouldAddDivider(index: Int, total: Int) = index < total - 1