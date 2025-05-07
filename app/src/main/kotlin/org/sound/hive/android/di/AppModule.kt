package org.sound.hive.android.di

import android.content.*
import androidx.room.*
import dagger.*
import dagger.hilt.*
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.*
import io.ktor.client.*
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.cookies.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.*
import okhttp3.*
import org.sound.hive.android.api.*
import org.sound.hive.android.api.MockSongApi
import org.sound.hive.android.data.repository.*
import org.sound.hive.android.data.repository.impl.*
import org.sound.hive.android.data.room.*
import org.sound.hive.android.service.*
import org.sound.hive.android.service.impl.*
import java.util.concurrent.*
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
    fun provideSongsRepository(songApi: SongApi): SongsRepository = SongsRepositoryImpl(songApi)

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

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "hive_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase) = db.userDao()

    @Singleton
    @Provides
    fun provideSongDao(db: AppDatabase) = db.songDao()

    @Provides
    @Singleton
    fun provideSongApi(client: HttpClient): SongApi = MockSongApi()

    @Provides
    @Singleton
    fun provideUserService(userRepository: UserRepository, apiService: ApiService): UserService =
        UserServiceImpl(userRepository, apiService)

    @Provides
    @Singleton
    fun provideSongService(
        songsRepository: SongsRepository,
        songApi: SongApi,
        appDatabase: AppDatabase
    ): SongService = SongServiceImpl(songsRepository, songApi, appDatabase)

    @Provides
    @Singleton
    fun provideFriendsService(friendsRepository: FriendsRepository): FriendsService =
        FriendsServiceImpl(friendsRepository)
}
