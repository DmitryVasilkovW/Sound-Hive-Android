package org.sound.hive.android.data.repository.impl

import org.sound.hive.android.R
import org.sound.hive.android.data.repository.SongsRepository
import org.sound.hive.android.model.Song
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SongsRepositoryImpl @Inject constructor() : SongsRepository {
    override fun getSongs(): List<Song> {
        return listOf(
            Song(
                id = 1,
                coverResId = R.drawable.velvet_underground_and_nico,
                title = "Sunday Morning",
                artist = "The Velvet Underground"
            ),
            Song(
                id = 2,
                coverResId = R.drawable.neutral_milk_hotel,
                title = "Holland, 1945",
                artist = "Neutral Milk Hotel"
            ),
            Song(
                id = 3,
                coverResId = R.drawable.panchiko,
                title = "D>E>A>T>H>M>E>T>A>L",
                artist = "Panchiko"
            ),
            Song(
                id = 4,
                coverResId = R.drawable.jpn,
                title = "Imperial",
                artist = "j^p^n"
            ),
            Song(
                id = 5,
                coverResId = R.drawable.hoover,
                title = "Electrolux",
                artist = "Hoover"
            ),
            Song(
                id = 6,
                coverResId = R.drawable.car_seat_headrest,
                title = "Sober to Death",
                artist = "Car Seat Headrest"
            )
        )
    }
}