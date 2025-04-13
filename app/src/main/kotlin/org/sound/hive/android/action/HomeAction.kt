package org.sound.hive.android.action

sealed class HomeAction {
    object LoadFriends : HomeAction()
    object LoadAlbumCover : HomeAction()
    object LoadInitialData : HomeAction()
    data class Navigate(val route: String) : HomeAction()
}
