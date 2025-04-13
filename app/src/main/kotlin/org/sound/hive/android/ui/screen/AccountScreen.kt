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
import androidx.hilt.navigation.compose.*
import androidx.lifecycle.compose.*
import androidx.navigation.*
import androidx.navigation.compose.*
import org.sound.hive.android.R
import org.sound.hive.android.effect.AccountSideEffect
import org.sound.hive.android.intent.AccountIntent
import org.sound.hive.android.ui.element.*
import org.sound.hive.android.ui.theme.*
import org.sound.hive.android.viewModel.AccountViewModel

@Composable
@Preview
fun AccountScreenPreview() {
    SoundHiveAndroid {
        AccountScreen(rememberNavController())
    }
}

@Composable
fun AccountScreen(
    navController: NavController,
    viewModel: AccountViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is AccountSideEffect.NavigateBack -> {
                    navController.navigate(sideEffect.route)
                }
            }
        }
    }

    SoundHiveAndroid {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp)
        ) {
            val (header, avatar, name, email) = createRefs()

            AccountHeader(
                modifier = Modifier.constrainAs(header) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            ) {
                viewModel.processIntent(AccountIntent.NavigateBack)
            }

            AccountAvatar(
                modifier = Modifier.constrainAs(avatar) {
                    top.linkTo(header.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                avatarId = state.user.avatarId
            )

            AccountName(
                name = state.user.name,
                modifier = Modifier.constrainAs(name) {
                    top.linkTo(avatar.bottom, margin = 12.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )

            AccountEmail(
                email = state.user.email,
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
private fun AccountHeader(
    modifier: Modifier = Modifier,
    processNavigateBackIcon: () -> Unit
) {
    ConstraintLayout(modifier = modifier.fillMaxWidth()) {
        val header = createRef()
        ScreenHeaderWithSettings(
            title = stringResource(R.string.account_title),
            modifier = Modifier.constrainAs(header) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            processNavigateBackIcon = { processNavigateBackIcon() }
        )
    }
}

@Composable
private fun AccountAvatar(modifier: Modifier = Modifier, avatarId: Int) {
    Image(
        painter = painterResource(avatarId),
        contentDescription = "Profile Picture",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .border(
                2.dp,
                MaterialTheme.colorScheme.primary,
                RoundedCornerShape(20.dp)
            )
    )
}

@Composable
private fun AccountName(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = modifier
    )
}

@Composable
private fun AccountEmail(email: String, modifier: Modifier = Modifier) {
    Text(
        text = email,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
        modifier = modifier
    )
}
