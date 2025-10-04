package com.revan.hanged.domain.model

import com.google.gson.annotations.SerializedName
import com.revan.hanged.data.remote.dto.PlayerDTO

data class RoomState(
    @SerializedName("currentTurn")
    val currentTurn: String?,
    @SerializedName("discovered")
    val discovered: List<String>?,
    @SerializedName("players")
    val players: List<Player>,
    @SerializedName("roomId")
    val roomId: String,
    @SerializedName("started")
    val started: Boolean,
    @SerializedName("wrongGuesses")
    val wrongGuesses: Int
)