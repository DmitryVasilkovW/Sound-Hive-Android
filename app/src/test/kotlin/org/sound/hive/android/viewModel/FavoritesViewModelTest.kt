package org.sound.hive.android.viewModel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.sound.hive.android.action.FavoritesAction
import org.sound.hive.android.effect.FavoritesSideEffect
import org.sound.hive.android.intent.FavoritesIntent
import org.sound.hive.android.service.SongService
import java.lang.reflect.Method


@ExperimentalCoroutinesApi
class FavoritesViewModelTest {

    private lateinit var viewModel: FavoritesViewModel
    private val songService: SongService = mock()

    private val processIntentMethod: Method by lazy {
        FavoritesViewModel::class.java
            .getDeclaredMethod("processIntentInternal", FavoritesIntent::class.java)
            .apply { isAccessible = true }
    }

    private val processActionMethod: Method by lazy {
        FavoritesViewModel::class.java
            .getDeclaredMethod("processAction", FavoritesAction::class.java)
            .apply { isAccessible = true }
    }

    private val navigateMethod: Method by lazy {
        FavoritesViewModel::class.java
            .getDeclaredMethod("navigate")
            .apply { isAccessible = true }
    }

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        viewModel = FavoritesViewModel(songService)
    }

    @org.junit.jupiter.api.Test
    fun `processIntent LoadInitialData should return LoadInitialData action`() {
        val action = processIntentMethod.invoke(viewModel, FavoritesIntent.LoadInitialData)
                as FavoritesAction

        MatcherAssert.assertThat(action, Matchers.`is`(FavoritesAction.LoadInitialData))
    }

    @org.junit.jupiter.api.Test
    fun `processIntent NavigateBack should return NavigateBack action`() {
        val action =
            processIntentMethod.invoke(viewModel, FavoritesIntent.NavigateBack) as FavoritesAction

        MatcherAssert.assertThat(action, Matchers.`is`(FavoritesAction.NavigateBack))
    }

    @org.junit.jupiter.api.Test
    fun `processAction NavigateBack should emit NavigateBack side effect`() = runTest {
        val testEffect = mutableListOf<FavoritesSideEffect>()
        viewModel.sideEffect.collect { testEffect.add(it) }

        processActionMethod.invoke(viewModel, FavoritesAction.NavigateBack)

        MatcherAssert.assertThat(testEffect.size, Matchers.`is`(1))
        MatcherAssert.assertThat(
            testEffect[0],
            Matchers.`is`(FavoritesSideEffect.NavigateBack(route = "home_route"))
        )
    }

    @org.junit.jupiter.api.Test
    fun `navigate should emit NavigateBack side effect`() = runTest {
        val testEffect = mutableListOf<FavoritesSideEffect>()
        viewModel.sideEffect.collect { testEffect.add(it) }

        navigateMethod.invoke(viewModel)


        MatcherAssert.assertThat(testEffect.size, Matchers.`is`(1))
        MatcherAssert.assertThat(
            testEffect[0],
            Matchers.`is`(FavoritesSideEffect.NavigateBack(route = "home_route"))
        )
    }

    @org.junit.jupiter.api.Test
    fun `processAction LoadSongs should call song service`() = runTest {

        processActionMethod.invoke(viewModel, FavoritesAction.LoadSongs)

        verify(songService).getAllSongs()
    }
}