package org.sound.hive.android.viewModel

import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.hamcrest.*
import org.junit.jupiter.api.*
import org.mockito.Mockito.*
import org.sound.hive.android.action.*
import org.sound.hive.android.intent.*
import org.sound.hive.android.service.*
import java.lang.reflect.*


@ExperimentalCoroutinesApi
class FavoritesViewModelTest {

    private lateinit var viewModel: FavoritesViewModel
    private val songService: SongService = mock()

    private val processIntentMethod: Method by lazy {
        FavoritesViewModel::class.java
            .getDeclaredMethod("processIntentInternal", FavoritesIntent::class.java)
            .apply { isAccessible = true }
    }

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        viewModel = FavoritesViewModel(songService)
    }

    @Test
    fun `processIntent LoadInitialData should return LoadInitialData action`() {
        val action = processIntentMethod.invoke(viewModel, FavoritesIntent.LoadInitialData)
                as FavoritesAction

        MatcherAssert.assertThat(action, Matchers.`is`(FavoritesAction.LoadInitialData))
    }

    @Test
    fun `processIntent NavigateBack should return NavigateBack action`() {
        val action =
            processIntentMethod.invoke(viewModel, FavoritesIntent.NavigateBack) as FavoritesAction

        MatcherAssert.assertThat(action, Matchers.`is`(FavoritesAction.NavigateBack))
    }
}
