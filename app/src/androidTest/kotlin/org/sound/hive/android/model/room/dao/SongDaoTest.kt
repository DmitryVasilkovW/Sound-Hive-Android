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
class SongDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var songDao: SongDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        songDao = database.songDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertAndGetSong() = runBlocking {
        val song = SongEntity(idTrack = "123")
        songDao.insertSong(song)

        val loadedSong = songDao.getSongById("123")
        assertNotNull(loadedSong)
        assertTrue("123" == loadedSong?.idTrack)
    }

    @Test
    fun deleteSongById_removesSong() = runBlocking {
        val song = SongEntity(idTrack = "123")
        songDao.insertSong(song)

        songDao.deleteSongById("123")
        val loadedSong = songDao.getSongById("123")
        assertNull(loadedSong)
    }
}
