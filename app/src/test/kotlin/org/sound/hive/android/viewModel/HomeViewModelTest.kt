package org.sound.hive.android.viewModel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertFalse
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import org.sound.hive.android.R
import org.sound.hive.android.effect.HomeSideEffect
import org.sound.hive.android.intent.HomeIntent
import org.sound.hive.android.model.Friend
import org.sound.hive.android.service.FriendsService
import java.lang.reflect.Method
import kotlin.test.assertEquals


@ExperimentalCoroutinesApi
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private val friendsService: FriendsService = mock()

    private val testFriends = listOf(
        Friend(
            photoResId = R.drawable.ic_avatar_default,
            name = "John Doe",
            song = "Song 1",
            artist = "Artist 1"
        ),
        Friend(
            photoResId = R.drawable.ic_avatar_default,
            name = "Jane Smith",
            song = "Song 2",
            artist = "Artist 2"
        )
    )

    private val loadFriendsMethod: Method by lazy {
        HomeViewModel::class.java
            .getDeclaredMethod("loadFriends")
            .apply { isAccessible = true }
    }

    private val navigateMethod: Method by lazy {
        HomeViewModel::class.java
            .getDeclaredMethod("navigate", String::class.java)
            .apply { isAccessible = true }
    }

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        viewModel = HomeViewModel(friendsService)
    }

    // паша))
    @Test
    fun `loadFriends should update state with friends list`() = runTest {
        whenever(friendsService.getAllFriends()).thenReturn(testFriends)

        viewModel.processIntent(HomeIntent.LoadInitialData)

        assertEquals(testFriends, viewModel.state.value.friends)
        assertFalse(viewModel.state.value.isLoading)
    }
}
