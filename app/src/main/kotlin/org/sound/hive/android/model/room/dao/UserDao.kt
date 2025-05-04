package org.sound.hive.android.model.room.dao

import androidx.room.*
import org.sound.hive.android.model.room.entity.*

@Dao
interface UserDao {
    @Insert
    fun insert(user: UserEntity): Long

    @get:Query("SELECT * FROM users")
    val all: MutableList<UserEntity>?

    @Delete
    fun delete(user: UserEntity?)
}
