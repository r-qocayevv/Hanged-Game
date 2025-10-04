package com.revan.hanged.data.remote.dto.game_detail


import com.google.gson.annotations.SerializedName
import com.revan.hanged.data.remote.dto.GameDTO

data class GameDetailDataDTO(
    @SerializedName("game")
    val game: GameDTO
)