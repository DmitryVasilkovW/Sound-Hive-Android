package org.sound.hive.android.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.sound.hive.android.action.AccountAction
import org.sound.hive.android.data.repository.UserRepository
import org.sound.hive.android.effect.AccountSideEffect
import org.sound.hive.android.intent.AccountIntent
import org.sound.hive.android.model.account.AccountState
import javax.inject.Inject

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
