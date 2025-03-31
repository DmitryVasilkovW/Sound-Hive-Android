package org.sound.hive.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import org.sound.hive.android.data.repository.FriendsRepository
import org.sound.hive.android.data.repository.FriendsRepositoryImpl


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFriendsRepository(): FriendsRepository = FriendsRepositoryImpl()
}
