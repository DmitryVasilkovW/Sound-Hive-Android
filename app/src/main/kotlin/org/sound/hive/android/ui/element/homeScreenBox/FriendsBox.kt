package org.sound.hive.android.ui.element.homeScreenBox

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.sound.hive.android.R
import org.sound.hive.android.model.Friend
import org.sound.hive.android.ui.element.FriendItem

@Composable
fun FriendsBox(modifier: Modifier) {
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