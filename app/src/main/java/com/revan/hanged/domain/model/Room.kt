package com.revan.hanged.domain.model

import com.revan.hanged.domain.RoomStatus


data class Room (
    val createdAt: String,
    val difficulty: String,
    val language: String,
    val maxPlayers: Int,
    var playerCount : Int = 0,
    val roomId: String,
    val roomName: String,
    val status: RoomStatus
)