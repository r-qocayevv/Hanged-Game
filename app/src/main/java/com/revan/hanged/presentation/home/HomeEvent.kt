package com.revan.hanged.presentation.home

import com.revan.hanged.domain.model.Room

sealed interface HomeEvent {

    data class ShowRoomStatusGuide (val showBottomSheet: Boolean) : HomeEvent
    data class JoinRoom (val room : Room,val userId : String,val username : String) : HomeEvent

}