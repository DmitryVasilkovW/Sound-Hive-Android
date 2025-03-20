package org.sound.hive.android.ui.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.layout.*
import androidx.compose.ui.res.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*
import androidx.constraintlayout.compose.*
import androidx.navigation.*
import androidx.navigation.compose.*
import org.sound.hive.android.R
import org.sound.hive.android.ui.element.*
import org.sound.hive.android.ui.theme.*

@Composable
@Preview
fun AccountScreenPreview() {
    SoundHiveAndroid {
        AccountScreen(rememberNavController())
    }
}

@Composable
fun AccountScreen(navController: NavController) {
    SoundHiveAndroid {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {
            val (header, avatar, name, email) = createRefs()

            AccountHeader(
                navController,
                modifier = Modifier.constrainAs(header) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )

            Image(
                painter = painterResource(R.drawable.ic_avatar_default),
                contentDescription = "Profile Picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .border(
                        2.dp,
                        MaterialTheme.colorScheme.primary,
                        RoundedCornerShape(20.dp)
                    )
                    .constrainAs(avatar) {
                        top.linkTo(header.bottom, margin = 16.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            Text(
                text = "Bazis",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.constrainAs(name) {
                    top.linkTo(avatar.bottom, margin = 12.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )

            Text(
                text = "tmp@hive.com",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                modifier = Modifier.constrainAs(email) {
                    top.linkTo(name.bottom, margin = 8.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
        }
    }
}

@Composable
private fun AccountHeader(navController: NavController, modifier: Modifier = Modifier) {
    ConstraintLayout(
        modifier = modifier.fillMaxWidth()
    ) {
        val header = createRef()

        ScreenHeaderWithSettings(
            navController,
            stringResource(R.string.account_title),
            modifier = Modifier.constrainAs(header) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
    }
}
