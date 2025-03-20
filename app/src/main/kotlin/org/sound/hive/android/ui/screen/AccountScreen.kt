package org.sound.hive.android.ui.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*
import androidx.constraintlayout.compose.*
import androidx.navigation.*
import androidx.navigation.compose.*
import org.sound.hive.android.R

@Composable
@Preview
fun AccountScreenPreview() {
    AccountScreen(rememberNavController())
}

@Composable
fun AccountScreen(navController: NavController) {
    val user = remember { mutableStateOf(User("Bazis", "pochta@abc.com", R.drawable.ic_avatar_default)) }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val (avatar, name, email, editButton, logoutButton, backButton) = createRefs()

        Image(
            painter = painterResource(id = user.value.profilePicture),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Gray, CircleShape)
                .constrainAs(avatar) {
                    top.linkTo(parent.top, margin = 32.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Text(
            text = user.value.name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(name) {
                top.linkTo(avatar.bottom, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        Text(
            text = user.value.email,
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.constrainAs(email) {
                top.linkTo(name.bottom, margin = 8.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        Button(
            onClick = { /* TODO: Добавить логику редактирования */ },
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(editButton) {
                    top.linkTo(email.bottom, margin = 32.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Text(text = "Редактировать профиль")
        }

        Button(
            onClick = { /* TODO: Добавить выход из аккаунта */ },
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(logoutButton) {
                    top.linkTo(editButton.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text(text = "Выйти", color = Color.White)
        }

        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(backButton) {
                    top.linkTo(logoutButton.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Text(text = "Назад")
        }
    }
}

data class User(val name: String, val email: String, val profilePicture: Int)
