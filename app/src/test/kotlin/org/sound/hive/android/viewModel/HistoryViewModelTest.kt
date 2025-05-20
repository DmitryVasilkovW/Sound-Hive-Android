package org.sound.hive.android.viewModel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.sound.hive.android.action.HistoryAction
import org.sound.hive.android.effect.HistorySideEffect
import org.sound.hive.android.intent.HistoryIntent
import org.sound.hive.android.service.SongService
import java.lang.reflect.Method

@ExperimentalCoroutinesApi
class HistoryViewModelTest {

    private lateinit var viewModel: HistoryViewModel
    private val songService: SongService = mock()

    private val processIntentMethod: Method by lazy {
        HistoryViewModel::class.java
            .getDeclaredMethod("processIntentInternal", HistoryIntent::class.java)
            .apply { isAccessible = true }
    }

    private val processActionMethod: Method by lazy {
        HistoryViewModel::class.java
            .getDeclaredMethod("processAction", HistoryAction::class.java)
            .apply { isAccessible = true }
    }

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        viewModel = HistoryViewModel(songService)
    }

    @Test
    fun `processIntent LoadInitialData should return LoadInitialData action`() {

        val action =
            processIntentMethod.invoke(viewModel, HistoryIntent.LoadInitialData) as HistoryAction


        Assertions.assertEquals(HistoryAction.LoadInitialData, action)
    }
}
