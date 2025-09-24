package com.revan.hanged.data.dto


import com.google.firebase.Timestamp
import com.google.gson.annotations.SerializedName
import com.revan.hanged.domain.model.Game

data class GameDTO(
    @SerializedName("createdAt")
    val createdAt: String = "",
    @SerializedName("difficulty")
    val difficulty: String = "",
    @SerializedName("language")
    val language: String = "",
    @SerializedName("players")
    val players: List<PlayerDTO> = listOf(),
    @SerializedName("roomId")
    val roomId: String = "",
    @SerializedName("roomName")
    val roomName: String = "",
    @SerializedName("winner")
    val winner: WinnerDTO? = WinnerDTO(),
    @SerializedName("word")
    val word: String = ""
)

fun GameDTO.toGame() : Game {
    return Game (
        createdAt = createdAt,
        difficulty = difficulty,
        language = language,
        players = players.map { it.toPlayer() },
        roomId = roomId,
        roomName = roomName,
        winner = winner?.toWinner(),
        word = word

    )
}