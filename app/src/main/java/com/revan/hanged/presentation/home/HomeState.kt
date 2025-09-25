package com.revan.hanged.presentation.home

import com.revan.hanged.domain.model.Game
import com.revan.hanged.domain.model.Room

data class HomeState(
    val isVisibleRoomStatusGuide: Boolean = false,
    val isLoading : Boolean = false,
    val rooms: List<Room> = emptyList(),
    val games: List<Game> = emptyList(),
    val availableRoomCount: Int = 0
)