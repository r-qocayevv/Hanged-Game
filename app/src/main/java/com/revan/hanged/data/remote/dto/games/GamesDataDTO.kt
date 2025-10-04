package com.revan.hanged.data.remote.dto.games


import com.google.gson.annotations.SerializedName
import com.revan.hanged.data.remote.dto.GameDTO

data class GamesDataDTO(
    @SerializedName("games")
    val games: List<GameDTO>
)