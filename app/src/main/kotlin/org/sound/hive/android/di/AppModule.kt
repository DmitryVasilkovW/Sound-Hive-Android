package org.sound.hive.android.di

import dagger.*
import dagger.hilt.*
import dagger.hilt.components.*
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.cookies.AcceptAllCookiesStorage
import io.ktor.client.plugins.cookies.HttpCookies
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.logging.Logger
import kotlinx.serialization.json.Json
import okhttp3.ConnectionPool
import okhttp3.internal.concurrent.TaskRunner.Companion.logger
import org.sound.hive.android.api.ApiService
import org.sound.hive.android.api.ApiServiceImpl
import org.sound.hive.android.data.repository.*
import org.sound.hive.android.data.repository.impl.*
import java.util.concurrent.TimeUnit
import javax.inject.*


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFriendsRepository(): FriendsRepository = FriendsRepositoryImpl()

    @Provides
    @Singleton
    fun provideUserRepository(): UserRepository = UserRepositoryImpl()

    @Provides
    @Singleton
    fun provideSongsRepository(): SongsRepository = SongsRepositoryImpl()

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(OkHttp) {
            engine {
                config {
                    connectTimeout(15, TimeUnit.SECONDS)
                    readTimeout(15, TimeUnit.SECONDS)
                    writeTimeout(15, TimeUnit.SECONDS)

                    // Пул соединений
                    connectionPool(ConnectionPool(50, 5, TimeUnit.MINUTES))

                    // Повторное соединение при ошибке
                    retryOnConnectionFailure(true)

                    // Следование редиректам
                    followRedirects(true)
                    followSslRedirects(true)
                }
            }

            install(HttpCookies) {
                storage = AcceptAllCookiesStorage()
            }

            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    prettyPrint = true
                })
            }

            install(HttpTimeout) {
                requestTimeoutMillis = 15000
                connectTimeoutMillis = 15000
                socketTimeoutMillis = 15000
            }
        }
    }

    @Provides
    @Singleton
    fun provideApiService(client: HttpClient): ApiService = ApiServiceImpl(client)
}
