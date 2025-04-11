package org.sound.hive.android.viewModel.account

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.sound.hive.android.action.*
import org.sound.hive.android.data.repository.*
import org.sound.hive.android.effect.*
import org.sound.hive.android.intent.*
import org.sound.hive.android.model.account.*
import javax.inject.*

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val stateMutable = MutableStateFlow(AccountState())
    val state: StateFlow<AccountState> = stateMutable.asStateFlow()

    private val sideEffectMutable = MutableSharedFlow<AccountSideEffect>()

    init {
        processIntent(AccountIntent.LoadInitialData)
    }

    fun processIntent(intent: AccountIntent) {
        val action = when (intent) {
            is AccountIntent.LoadInitialData -> AccountAction.LoadInitialData
        }

        processAction(action)
    }

    private fun processAction(action: AccountAction) {
        when (action) {
            is AccountAction.Navigate -> navigate(action.route)
            is AccountAction.LoadInitialData -> loadInitialData()
            is AccountAction.LoadUser -> loadUser(239L)
        }
    }

    private fun navigate(route: String) {
        viewModelScope.launch {
            sideEffectMutable.emit(AccountSideEffect.Navigate(route))
        }
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            stateMutable.update { it.copy(isLoading = true) }
            processAction(AccountAction.LoadUser)
        }
    }

    private fun loadUser(id: Long) {
        viewModelScope.launch {
            stateMutable.update { it.copy(isLoading = true) }
            val user = userRepository.getUserById(id)
            stateMutable.update {
                it.copy(
                    user = user,
                    isLoading = false
                )
            }
        }
    }
}
