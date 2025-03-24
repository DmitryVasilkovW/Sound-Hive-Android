package org.sound.hive.android.ui.sheets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.sound.hive.android.ui.element.DisketteCard

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
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}
