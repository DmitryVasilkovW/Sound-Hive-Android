package org.sound.hive.android.ui.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.layout.*
import androidx.compose.ui.res.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*
import androidx.navigation.*
import androidx.navigation.compose.*
import org.sound.hive.android.R
import org.sound.hive.android.model.*
import org.sound.hive.android.ui.common.*
import org.sound.hive.android.ui.element.*
import org.sound.hive.android.ui.theme.*

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
            HomeHeader()

            ImageRow(navController)

            Spacer(modifier = Modifier.height(16.dp))

            BoxesSection(navController)
        }
    }
}

@Composable
private fun HomeHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
    ) {
        SettingsButton()
    }
}

@Composable
private fun ImageRow(navController: NavController) {
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

        AccountRow(imageModifier, navController)
    }
}

@Composable
private fun AccountRow(modifier: Modifier, navController: NavController) {
    Box(
        modifier = modifier
            .clickable {
                navController.navigate(accountRoute)
            },
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(R.drawable.ic_avatar_default),
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = modifier.border(
                2.dp,
                MaterialTheme.colorScheme.primary,
                RoundedCornerShape(20.dp),
            )
        )
    }
}

@Composable
private fun BoxesSection(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            val boxModifier = Modifier
                .weight(1f)
                .aspectRatio(1f)

            HistoryBox(boxModifier, navController)
            FavoritesBox(boxModifier, navController)
        }
        FriendsBox(
            Modifier
                .fillMaxWidth()
                .weight(1f),
        )
    }
}

@Composable
private fun FavoritesBox(modifier: Modifier, navController: NavController) {
    Box(
        modifier = modifier
            .background(
                MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(20.dp),
            )
            .clickable {
                navController.navigate(favoritesRoute)
            },
        contentAlignment = Alignment.Center
    ) {
        val beeDecorationConfigs = listOf(
            DecorationConfig(
                alignment = Alignment.CenterEnd,
                rotationDegrees = 225f,
                modifier = Modifier.padding(25.dp).offset(y = (-15).dp)
            ),
            DecorationConfig(
                alignment = Alignment.BottomEnd,
                rotationDegrees = 290f,
                modifier = Modifier.padding(20.dp)
            ),
            DecorationConfig(
                alignment = Alignment.CenterStart,
                rotationDegrees = 135f,
                modifier = Modifier.padding(10.dp)
            )
        )

        beeDecorationConfigs.forEach { (alignment, rotation, extraModifier) ->
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
                text = stringResource(R.string.favorites_name),
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

@Composable
private fun FriendsBox(modifier: Modifier) {
    val friendsList = remember {
        listOf(
            Friend(
                photoResId = R.drawable.ic_avatar_default_light,
                name = "Alice",
                song = "Honestly?",
                artist = "American Football",
            ),
            Friend(
                photoResId = R.drawable.ic_avatar_default_light,
                name = "Bob",
                song = "Gonna Leave You",
                artist = "Queens of the Stone Age",
            ),
        )
    }

    Box(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(16.dp),
            )
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_bee_small_light),
            contentDescription = "Bee Light Icon",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(5.dp)
                .offset(y = (-10).dp)
                .rotate(225f)
                .size(40.dp),
        )

        Column {
            Text(
                text = stringResource(R.string.friends_name),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(bottom = 12.dp),
            )

            LazyColumn(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                items(friendsList) { friend ->
                    FriendItem(friend)
                }
            }
        }
    }
}

@Composable
private fun HistoryBox(modifier: Modifier, navController: NavController) {
    Box(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(20.dp),
            )
            .clickable {
                navController.navigate(historyRoute)
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

private data class DecorationConfig(
    val alignment: Alignment,
    val rotationDegrees: Float,
    val modifier: Modifier
)
