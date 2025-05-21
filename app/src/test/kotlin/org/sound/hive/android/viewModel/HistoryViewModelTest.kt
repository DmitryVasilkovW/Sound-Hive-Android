package org.sound.hive.android.viewModel

import kotlinx.coroutines.*
import kotlinx.coroutines.test.*
import org.junit.jupiter.api.*
import org.mockito.Mockito.*
import org.sound.hive.android.action.*
import org.sound.hive.android.intent.*
import org.sound.hive.android.service.*
import java.lang.reflect.*

@ExperimentalCoroutinesApi
class HistoryViewModelTest {

    private lateinit var viewModel: HistoryViewModel
    private val songService: SongService = mock()

    private val processIntentMethod: Method by lazy {
        HistoryViewModel::class.java
            .getDeclaredMethod("processIntentInternal", HistoryIntent::class.java)
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
