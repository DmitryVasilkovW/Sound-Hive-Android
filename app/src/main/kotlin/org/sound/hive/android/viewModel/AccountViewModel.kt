package org.sound.hive.android.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.sound.hive.android.action.AccountAction
import org.sound.hive.android.effect.AccountSideEffect
import org.sound.hive.android.service.UserService
import org.sound.hive.android.effect.HomeSideEffect
import org.sound.hive.android.intent.AccountIntent
import org.sound.hive.android.model.User
import org.sound.hive.android.model.account.AccountState
import org.sound.hive.android.ui.common.homeRoute
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val userService: UserService
) : ViewModel() {
    private val stateMutable = MutableStateFlow(AccountState())
    val state = stateMutable.asStateFlow()

    private val sideEffectMutable = MutableSharedFlow<AccountSideEffect>()
    val sideEffect = sideEffectMutable.asSharedFlow()

    init {
        processIntent(AccountIntent.LoadInitialData)
    }

    fun processIntent(intent: AccountIntent) {
        val action = when (intent) {
            is AccountIntent.LoadInitialData -> AccountAction.LoadInitialData
            is AccountIntent.NavigateBack -> AccountAction.NavigateBack
        }

        processAction(action)
    }

    private fun processAction(action: AccountAction) {
        when (action) {
            is AccountAction.LoadInitialData -> loadInitialData()
            is AccountAction.LoadUser -> loadUser(239L)
            is AccountAction.NavigateBack -> navigateBack()
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            sideEffectMutable.emit(AccountSideEffect.NavigateBack(route = homeRoute))
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
            val user = userService.getUserById(id.toString())
            stateMutable.update {
                it.copy(
                    user = user ?: User(),
                    isLoading = false
                )
            }
        }
    }
}
