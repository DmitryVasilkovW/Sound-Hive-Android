package org.sound.hive.android.ui.screen

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.*

@Composable
@Preview
fun TrackDetailsScreenPreview() {
    TrackDetailsScreen("1")
}

@Composable
fun TrackDetailsScreen(trackId: String?) {
    Text(text = "Details for track $trackId")
}
