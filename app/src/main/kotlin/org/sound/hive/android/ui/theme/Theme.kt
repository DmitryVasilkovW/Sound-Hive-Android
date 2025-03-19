package org.sound.hive.android.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.*

private val lightColorScheme = lightColorScheme(
    background = eggshellWhite,
    onBackground = black,
    primary = graniteGray,
    secondary = saffronYellow,
    onSecondary = black,
    onPrimary = blondYellow,
)

@Composable
fun SoundHiveAndroid(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme,
        typography = typography,
        content = content
    )
}