package org.sound.hive.android.viewModel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.sound.hive.android.intent.AccountIntent
import org.sound.hive.android.model.User
import org.sound.hive.android.service.UserService

@ExperimentalCoroutinesApi
class AccountViewModelTest {

    private lateinit var viewModel: AccountViewModel
    private val userService: UserService = mock()

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        viewModel = AccountViewModel(userService)
    }

    @Test
    fun `init should trigger LoadInitialData`() = runTest {
        verify(userService).getUserById(anyString())
    }

    @Test
    fun `loadUser should update state with user data`() = runTest {
        val testUser = User(name = "Test User")
        whenever(userService.getUserById(anyString())).thenReturn(testUser)

        viewModel.processIntent(AccountIntent.LoadInitialData)

        Assertions.assertEquals(testUser, viewModel.state.value.user)
        Assertions.assertFalse(viewModel.state.value.isLoading)
    }
}
