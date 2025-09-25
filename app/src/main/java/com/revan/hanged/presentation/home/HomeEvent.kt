package com.revan.hanged.presentation.home

import com.revan.hanged.domain.model.JoinRoomInfo
import com.revan.hanged.domain.model.Room
import com.revan.hanged.navigation.ScreenRoute

sealed interface HomeEvent {

    data class ShowRoomStatusGuide (val showBottomSheet: Boolean) : HomeEvent
    data class JoinRoom (val roomInfo : JoinRoomInfo) : HomeEvent
    data object LogOut : HomeEvent
    data class OnNavigate (val route : ScreenRoute, val popUpTo : ScreenRoute? = null) : HomeEvent

}