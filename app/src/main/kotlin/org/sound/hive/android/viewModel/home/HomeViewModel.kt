package org.sound.hive.android.viewModel.home

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
import org.sound.hive.android.R
import org.sound.hive.android.action.HomeAction
import org.sound.hive.android.data.repository.FriendsRepository
import org.sound.hive.android.effect.HomeSideEffect
import org.sound.hive.android.intent.HomeIntent
import org.sound.hive.android.model.home.HomeState
import org.sound.hive.android.ui.common.accountRoute
import org.sound.hive.android.ui.common.favoritesRoute
import org.sound.hive.android.ui.common.historyRoute
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val friendsRepository: FriendsRepository
) : ViewModel() {

    private val stateMutable = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = stateMutable.asStateFlow()

    private val sideEffectMutable = MutableSharedFlow<HomeSideEffect>()
    val sideEffect: SharedFlow<HomeSideEffect> = sideEffectMutable.asSharedFlow()

    init {
        processIntent(HomeIntent.LoadInitialData)
    }

    fun processIntent(intent: HomeIntent) {
        val action = when (intent) {
            is HomeIntent.LoadInitialData -> HomeAction.LoadInitialData
            is HomeIntent.NavigateToAccount -> HomeAction.Navigate(accountRoute)
            is HomeIntent.NavigateToFavorites -> HomeAction.Navigate(favoritesRoute)
            is HomeIntent.NavigateToHistory -> HomeAction.Navigate(historyRoute)
        }

        processAction(action)
    }

    private fun processAction(action: HomeAction) {
        when (action) {
            is HomeAction.LoadFriends -> loadFriends()
            is HomeAction.LoadAlbumCover -> loadAlbumCover()
            is HomeAction.LoadInitialData -> loadInitialData()
            is HomeAction.Navigate -> navigate(action.route)
        }
    }

    private fun navigate(route: String) {
        viewModelScope.launch {
            sideEffectMutable.emit(HomeSideEffect.Navigate(route))
        }
    }

    private fun loadFriends() {
        viewModelScope.launch {
            stateMutable.update { it.copy(isLoading = true) }
            val friends = friendsRepository.getFriends()
            stateMutable.update {
                it.copy(
                    friends = friends,
                    isLoading = false
                )
            }
        }
    }

    private fun loadAlbumCover() {
        viewModelScope.launch {
            val albumCoverResId = R.drawable.velvet_underground_and_nico
            stateMutable.update {
                it.copy(albumCoverResId = albumCoverResId)
            }
        }
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            stateMutable.update { it.copy(isLoading = true) }
            processAction(HomeAction.LoadFriends)
            processAction(HomeAction.LoadAlbumCover)
        }
    }
}
