package org.sound.hive.android.ui.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.sound.hive.android.R
import org.sound.hive.android.ui.element.NavigationIcon
import org.sound.hive.android.ui.element.homeScreenBox.FavoritesBox
import org.sound.hive.android.ui.element.homeScreenBox.FriendsBox
import org.sound.hive.android.ui.element.homeScreenBox.HistoryBox
import org.sound.hive.android.ui.theme.SoundHiveAndroid

@Composable
@Preview
fun HomeScreenPreview() {
    SoundHiveAndroid {
        HomeScreen(rememberNavController())
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    SoundHiveAndroid {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                NavigationIcon(navController)

                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(R.drawable.ic_settings),
                        contentDescription = "Settings",
                        tint = MaterialTheme.colorScheme.onBackground,
                    )
                }
            }

            Row(modifier = Modifier.fillMaxWidth()) {
                val imageModifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(20.dp))

                Image(
                    painter = painterResource(R.drawable.velvet_underground_and_nico),
                    contentDescription = "Album Cover",
                    contentScale = ContentScale.Crop,
                    modifier = imageModifier,
                )

                Spacer(modifier = Modifier.width(16.dp))

                Image(
                    painter = painterResource(R.drawable.ic_avatar_default),
                    contentDescription = "Profile Picture",
                    contentScale = ContentScale.Crop,
                    modifier = imageModifier.border(
                        2.dp,
                        MaterialTheme.colorScheme.primary,
                        RoundedCornerShape(20.dp),
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    val boxModifier = Modifier.weight(1f).aspectRatio(1f)
                    HistoryBox(boxModifier, navController)
                    FavoritesBox(boxModifier)
                }
                FriendsBox(Modifier.fillMaxWidth().weight(1f))
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}