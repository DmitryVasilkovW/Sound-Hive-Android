package org.sound.hive.android.ui.element.homeScreenBox

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.sound.hive.android.R
import org.sound.hive.android.ui.common.history

@Composable
fun HistoryBox(modifier: Modifier, navController: NavController) {
    Box(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(20.dp),
            )
            .clickable {
                navController.navigate(history)
            },
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(R.drawable.ic_hexagon_background),
            contentDescription = "Hexagon Background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp)),
        )

        Image(
            painter = painterResource(R.drawable.ic_bee_small),
            contentDescription = "Bee Icon",
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(30.dp)
                .rotate(225f)
                .offset(x = (-2).dp, y = (-6).dp)
                .size(40.dp),
        )

        Text(
            text = stringResource(R.string.history_name),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSecondary,
        )
    }
}
