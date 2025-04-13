package org.sound.hive.android.di

import dagger.*
import dagger.hilt.*
import dagger.hilt.components.*
import org.sound.hive.android.data.repository.*
import org.sound.hive.android.data.repository.impl.*
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
}
