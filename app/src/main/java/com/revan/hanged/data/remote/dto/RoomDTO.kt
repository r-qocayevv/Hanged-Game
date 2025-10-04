package com.revan.hanged.data.remote.dto


import com.google.firebase.Timestamp
import com.google.gson.annotations.SerializedName
import com.revan.hanged.domain.model.Room
import com.revan.hanged.utils.firstCharToUpperCase
import com.revan.hanged.utils.getTimeAgo
import com.revan.hanged.utils.toRoomStatus

data class RoomsDTO(
    @SerializedName("createdAt")
    val createdAt: Timestamp = Timestamp.now(),
    @SerializedName("difficulty")
    val difficulty: String = "",
    @SerializedName("language")
    val language: String = "",
    @SerializedName("playerCount")
    val playerCount : Int = 0,
    @SerializedName("maxPlayers")
    val maxPlayers: Int = 0,
    @SerializedName("roomId")
    val roomId: String = "",
    @SerializedName("roomName")
    val roomName: String = "",
    @SerializedName("status")
    val status: String = ""
)

fun RoomsDTO.toRooms () : Room {
    return Room(
        createdAt = createdAt.getTimeAgo(),
        difficulty = difficulty.firstCharToUpperCase(),
        language = language,
        maxPlayers = maxPlayers,
        playerCount = playerCount,
        roomId = roomId,
        roomName = roomName,
        status = status.toRoomStatus()
    )
}