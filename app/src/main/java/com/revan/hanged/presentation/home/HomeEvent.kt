package com.revan.hanged.presentation.home

import com.revan.hanged.domain.model.RoomInfo
import com.revan.hanged.navigation.ScreenRoute

sealed interface HomeEvent {

    data class ShowRoomStatusGuide (val showBottomSheet: Boolean) : HomeEvent
    data class JoinRoom (val roomInfo : RoomInfo) : HomeEvent
    data class LogOut(val username : String) : HomeEvent
    data class OnNavigate (val route : ScreenRoute, val popUpTo : ScreenRoute? = null) : HomeEvent
    object RefreshPage : HomeEvent
    data class OnItemSelection (val tabIndex : Int): HomeEvent

}