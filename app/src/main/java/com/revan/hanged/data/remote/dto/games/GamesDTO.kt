package com.revan.hanged.data.remote.dto.games


import com.google.gson.annotations.SerializedName

data class GamesDTO(
    @SerializedName("data")
    val `data`: GamesDataDTO,
    @SerializedName("success")
    val success: Boolean
)