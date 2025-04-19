package org.sound.hive.android

import android.content.Intent
import android.os.*
import android.util.Log
import androidx.activity.*
import androidx.activity.compose.*
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.*
import androidx.navigation.compose.*
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import dagger.hilt.android.AndroidEntryPoint
import org.sound.hive.android.ui.common.*
import org.sound.hive.android.ui.screen.*

const val SPOTIFY_CLIENT_ID = "fd3e82118cc14b629932636c9572733d"
//const val SPOTIFY_REDIRECT_URI = "https://sound-hive.org/sound-hive-login/callback"
//const val SPOTIFY_REDIRECT_URI = "spotify-sdk://auth"
const val SPOTIFY_REDIRECT_URI = "http://127.0.0.1:8080/callback"
const val REQUEST_CODE = 1337

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SpotifyAuthScreen(onLoginClick = {
                Log.d("SpotifyAuth", "Нажата кнопка авторизации")

                val request = AuthorizationRequest.Builder(
                    SPOTIFY_CLIENT_ID,
                    AuthorizationResponse.Type.TOKEN,
                    SPOTIFY_REDIRECT_URI
                ).setScopes(arrayOf("user-library-read", "playlist-read-private"))
                    .build()

                Log.d("SpotifyAuth:", "Создан запрос")
                AuthorizationClient.openLoginActivity(this, REQUEST_CODE, request)
                Log.d("SpotifyAuth", "Открыта активность")
            })
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        Log.d("SpotifyAuth", "Зашел в onActivityResult 1")
        super.onActivityResult(requestCode, resultCode, intent)
        Log.d("SpotifyAuth", "Зашел в onActivityResult 2")
        if (requestCode == REQUEST_CODE) {
            val response = AuthorizationClient.getResponse(resultCode, intent)
            when (response.type) {
                AuthorizationResponse.Type.TOKEN -> {
                    Log.i("SpotifyAuth", "Успешная авторизация! Access token: ${response.accessToken}")
                }
                AuthorizationResponse.Type.ERROR -> {
                    Log.e("SpotifyAuth", "Ошибка авторизации: ${response.error}")
                }
                else -> {
                    Log.w("SpotifyAuth", "Неизвестный результат: ${response.type}")
                }
            }
        }
    }
}

@Composable
@Preview
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = homeRoute,
    ) {
        composable(homeRoute) {
            HomeScreen(navController)
        }

        composable(accountRoute) {
            AccountScreen(navController)
        }

        composable(historyRoute) {
            HistoryScreen(navController)
        }

        composable(favoritesRoute) {
            FavoritesScreen(navController)
        }
    }
}
