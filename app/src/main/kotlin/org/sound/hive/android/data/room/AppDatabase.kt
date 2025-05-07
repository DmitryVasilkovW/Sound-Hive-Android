package org.sound.hive.android.data.room

import androidx.room.*
import org.sound.hive.android.model.room.dao.*
import org.sound.hive.android.model.room.entity.*

@Database(entities = [UserEntity::class, SongEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun songDao(): SongDao
}
