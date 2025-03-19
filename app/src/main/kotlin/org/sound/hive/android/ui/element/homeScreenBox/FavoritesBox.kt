package org.sound.hive.android.ui.element.homeScreenBox

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.sound.hive.android.R

@Composable
fun FavoritesBox(modifier: Modifier) {
    Box(
        modifier = modifier.background(
            MaterialTheme.colorScheme.secondary,
            shape = RoundedCornerShape(20.dp),
        ),
        contentAlignment = Alignment.Center
    ) {
        listOf(
            Triple(Alignment.CenterEnd, 225f, Modifier.padding(25.dp).offset(y = (-15).dp)),
            Triple(Alignment.BottomEnd, 290f, Modifier.padding(20.dp)),
            Triple(Alignment.CenterStart, 135f, Modifier.padding(10.dp)),
        ).forEach { (alignment, rotation, extraModifier) ->
            Image(
                painter = painterResource(R.drawable.ic_bee_small),
                contentDescription = "Bee Icon",
                modifier = Modifier
                    .align(alignment)
                    .then(extraModifier)
                    .rotate(rotation)
                    .size(40.dp)
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize(),
        ) {
            Text(
                text = stringResource(R.string.favorites_box_name),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSecondary,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Icon(
                painterResource(R.drawable.ic_hive),
                contentDescription = "Hive Icon",
                modifier = Modifier.size(80.dp),
            )
        }
    }
}