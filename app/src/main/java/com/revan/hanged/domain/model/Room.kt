package com.revan.hanged.domain.model

import com.revan.hanged.domain.GameLanguage
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

fun Room.toJoinRoomInfo (username : String,userId : String) : RoomInfo {
    return RoomInfo(
        roomId = roomId,
        userId = userId,
        userName = username,
        difficulty = difficulty.lowercase(),
        gameLanguage = if (language == "en") GameLanguage.ENG else if (language == "az") GameLanguage.AZE else GameLanguage.TRY,
        maxPlayerCount = maxPlayers,
        currentPlayerCount = playerCount
    )
}