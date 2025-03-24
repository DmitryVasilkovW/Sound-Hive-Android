package org.sound.hive.android.ui.element

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BlurredBackground() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .blur(20.dp)
            .background(Color.Black.copy(alpha = 0.3f))
    )
}
