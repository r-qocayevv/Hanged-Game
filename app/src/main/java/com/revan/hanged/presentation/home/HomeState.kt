package com.revan.hanged.presentation.home

import com.revan.hanged.domain.model.Game
import com.revan.hanged.domain.model.Room

data class HomeState(
    val isVisibleRoomStatusGuide: Boolean = false,
    val isLoading : Boolean = false,
    val username : String = "",
    val userId : String = "",
    val rooms: List<Room> = emptyList(),
    val filteredRooms : List<Room> = emptyList(),
    val availableRoomCount: Int = 0,
    val isRefreshing: Boolean = false,
    val selectedTabIndex : Int = 0
)