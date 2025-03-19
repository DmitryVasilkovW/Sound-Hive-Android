package org.sound.hive.android.ui.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.sound.hive.android.R
import org.sound.hive.android.model.Friend
import org.sound.hive.android.ui.common.favoritesRoute
import org.sound.hive.android.ui.common.historyRoute
import org.sound.hive.android.ui.element.FriendItem
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
            HomeHeader()

            ImageRow()

            Spacer(modifier = Modifier.height(16.dp))

            BoxesSection(navController)
        }
    }
}

@Composable
private fun HomeHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        IconButton(onClick = {}) {
            Icon(
                painter = painterResource(R.drawable.ic_settings),
                contentDescription = "Settings",
                tint = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}

@Composable
private fun ImageRow() {
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
fun HistoryBox(modifier: Modifier, navController: NavController) {
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