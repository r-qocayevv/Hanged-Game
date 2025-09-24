package com.revan.hanged.domain

import androidx.compose.ui.graphics.Color
import com.revan.hanged.R

enum class RoomStatus (
    val label : String,
    val color : Color,
    val icon: Int,
    val description : String
) {

    WAITING (label = "Waiting", color = Color(0xFF42D591), icon = R.drawable.ic_waiting, description = "Room is waiting for players to join"),
    PLAYING (label = "Playing", color = Color(0xFFF9BB00), icon = R.drawable.ic_playing, description = "Game is currently in progress"),
    FULL (label = "Full", color = Color(0xFFF6647F), icon = R.drawable.ic_full, description = "Room has reached maximum capacity"),


}