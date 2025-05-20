package org.sound.hive.android.viewModel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.sound.hive.android.R
import org.sound.hive.android.effect.HomeSideEffect
import org.sound.hive.android.intent.HomeIntent
import org.sound.hive.android.model.Friend
import org.sound.hive.android.service.FriendsService
import org.sound.hive.android.ui.common.accountRoute
import org.sound.hive.android.ui.common.favoritesRoute
import org.sound.hive.android.ui.common.historyRoute

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private lateinit var mockFriendsService: MockFriendsService
    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        mockFriendsService = MockFriendsService()
        viewModel = HomeViewModel(mockFriendsService)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `init should load initial data`() = testScope.runTest {
        // Given - ViewModel is initialized in setup

        // When
        advanceUntilIdle() // Process all coroutines

        // Then
        val currentState = viewModel.state.value
        assertEquals(mockFriendsService.mockFriends, currentState.friends)
        assertEquals(R.drawable.velvet_underground_and_nico, currentState.albumCoverResId)
        assertFalse(currentState.isLoading)
    }

    @Test
    fun `processIntent with LoadInitialData should update state with friends and album cover`() = testScope.runTest {
        // Given
        val initialState = viewModel.state.value

        // When
        viewModel.processIntent(HomeIntent.LoadInitialData)
        advanceUntilIdle() // Process all coroutines

        // Then
        val currentState = viewModel.state.value
        assertEquals(mockFriendsService.mockFriends, currentState.friends)
        assertEquals(R.drawable.velvet_underground_and_nico, currentState.albumCoverResId)
        assertFalse(currentState.isLoading)
    }

    @Test
    fun `processIntent with NavigateToAccount should emit Navigate side effect with account route`() = testScope.runTest {
        // Given
        val sideEffects = mutableListOf<HomeSideEffect>()
        val job = launch {
            viewModel.sideEffect.toList(sideEffects)
        }

        // When
        viewModel.processIntent(HomeIntent.NavigateToAccount)
        advanceUntilIdle() // Process all coroutines

        // Then
        assertEquals(1, sideEffects.size)
        val sideEffect = sideEffects.first()
        assertTrue(sideEffect is HomeSideEffect.Navigate)
        assertEquals(accountRoute, (sideEffect as HomeSideEffect.Navigate).route)

        job.cancel() // Clean up the collection job
    }

    @Test
    fun `processIntent with NavigateToFavorites should emit Navigate side effect with favorites route`() = testScope.runTest {
        // Given
        val sideEffects = mutableListOf<HomeSideEffect>()
        val job = launch {
            viewModel.sideEffect.toList(sideEffects)
        }

        // When
        viewModel.processIntent(HomeIntent.NavigateToFavorites)
        advanceUntilIdle() // Process all coroutines

        // Then
        assertEquals(1, sideEffects.size)
        val sideEffect = sideEffects.first()
        assertTrue(sideEffect is HomeSideEffect.Navigate)
        assertEquals(favoritesRoute, (sideEffect as HomeSideEffect.Navigate).route)

        job.cancel() // Clean up the collection job
    }

    @Test
    fun `processIntent with NavigateToHistory should emit Navigate side effect with history route`() = testScope.runTest {
        // Given
        val sideEffects = mutableListOf<HomeSideEffect>()
        val job = launch {
            viewModel.sideEffect.toList(sideEffects)
        }

        // When
        viewModel.processIntent(HomeIntent.NavigateToHistory)
        advanceUntilIdle() // Process all coroutines

        // Then
        assertEquals(1, sideEffects.size)
        val sideEffect = sideEffects.first()
        assertTrue(sideEffect is HomeSideEffect.Navigate)
        assertEquals(historyRoute, (sideEffect as HomeSideEffect.Navigate).route)

        job.cancel() // Clean up the collection job
    }

    /**
     * Mock implementation of FriendsService for testing
     */
    private class MockFriendsService : FriendsService {
        val mockFriends = listOf(
            Friend(
                photoResId = R.drawable.ic_avatar_default,
                name = "Test Friend 1",
                song = "Test Song 1",
                artist = "Test Artist 1"
            ),
            Friend(
                photoResId = R.drawable.ic_avatar_default,
                name = "Test Friend 2",
                song = "Test Song 2",
                artist = "Test Artist 2"
            )
        )

        override suspend fun getAllFriends(): List<Friend> = mockFriends

        override suspend fun getFriendById(friendId: String): Friend? = null

        override suspend fun addFriend(friend: Friend): Boolean = true

        override suspend fun removeFriend(friendId: String): Boolean = true

        override suspend fun getFriendsWithSimilarTaste(): List<Friend> = emptyList()
    }
}