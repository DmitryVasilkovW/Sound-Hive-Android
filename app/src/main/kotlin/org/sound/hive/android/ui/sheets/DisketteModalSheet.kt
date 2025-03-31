package org.sound.hive.android.ui.sheets

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.unit.*
import org.sound.hive.android.ui.element.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisketteModalSheet(showSheet: Boolean, onDismiss: () -> Unit, sheetState: SheetState) {
    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            modifier = Modifier.fillMaxSize(),
            sheetState = sheetState
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                DisketteCard(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .rotate(90f),
                )
            }
        }
    }
}
