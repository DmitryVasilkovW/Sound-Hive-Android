package org.sound.hive.android.data.repository.impl

import org.sound.hive.android.data.repository.*
import org.sound.hive.android.model.*
import javax.inject.*

@Singleton
class UserRepositoryImpl @Inject constructor() : UserRepository {
    override suspend fun getUserById(id: Long): User = User(
        name = "Bazis",
        email = "tmp@hive.com"
    )
}
