package com.revan.hanged.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.revan.hanged.domain.model.Game

data class GameDTO(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("difficulty")
    val difficulty: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("wrongGuesses")
    val wrongGuesses : Int,
    @SerializedName("players")
    val players: List<PlayerDTO>,
    @SerializedName("roomId")
    val roomId: String,
    @SerializedName("roomName")
    val roomName: String,
    @SerializedName("winner")
    val winner: WinnerDTO,
    @SerializedName("word")
    val word: String
)


fun GameDTO.toGame(): Game {
    return Game(
        createdAt = createdAt,
        difficulty = difficulty,
        language = language,
        players = players.map { it.toPlayer() },
        roomId = roomId,
        wrongGuesses = wrongGuesses,
        winner = winner.toWinner(),
        word = word,
        roomName = roomName
    )
}