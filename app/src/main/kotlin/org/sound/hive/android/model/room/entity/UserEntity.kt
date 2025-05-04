package org.sound.hive.android.model.room.entity

import androidx.room.*
import org.sound.hive.android.R

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name = "name")
    val name: String = "Undefined",

    @ColumnInfo(name = "email")
    val email: String = "Undefined",

    @ColumnInfo(name = "avatarId")
    val avatarId: Int = R.drawable.ic_avatar_default,
)
