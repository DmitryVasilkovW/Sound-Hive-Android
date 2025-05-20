package org.sound.hive.android.model.room.dao

import android.content.*
import androidx.room.*
import androidx.test.core.app.*
import androidx.test.ext.junit.runners.*
import junit.framework.TestCase.*
import kotlinx.coroutines.*
import org.junit.*
import org.junit.runner.*
import org.sound.hive.android.data.room.*
import org.sound.hive.android.model.room.entity.*

@RunWith(AndroidJUnit4::class)
class UserDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var userDao: UserDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        userDao = database.userDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertAndGetSong() = runBlocking {
        val user = UserEntity(name = "Bazis", email = "aboba239@tmp.com")
        userDao.insert(user)
        userDao.insert(user)

        val users = userDao.all
        assertNotNull(user)
        assertTrue(users?.size == 2)
        assertTrue(users?.any { it.name == user.name } == true)
    }

    @Test
    fun deleteSongById_removesSong() = runBlocking {
        val user = UserEntity(name = "Pablo", email = "ekb@239.com")
        val id = userDao.insert(user)

        val userForDelete = userDao.getUserById(id.toString())
        userDao.delete(userForDelete!!)
        val users = userDao.all
        assertTrue(users?.size == 0)
    }
}
