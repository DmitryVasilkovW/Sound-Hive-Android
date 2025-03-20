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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
        ) {
            AccountHeader(navController)

            Spacer(modifier = Modifier.height(16.dp))

            AccountInfoSection()
        }
    }
}

@Composable
private fun AccountHeader(navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ScreenHeaderWithSettings(navController, stringResource(R.string.account_title))
    }
}

@Composable
private fun AccountInfoSection() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(R.drawable.ic_avatar_default),
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier.border(
                2.dp,
                MaterialTheme.colorScheme.primary,
                RoundedCornerShape(20.dp),
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Bazis",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "tmp@hive.com",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
        )
    }
}
