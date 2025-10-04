package com.revan.hanged.data.remote.dto.game_detail


import com.google.gson.annotations.SerializedName

data class GameDetailDTO(
    @SerializedName("data")
    val `data`: GameDetailDataDTO,
    @SerializedName("success")
    val success: Boolean
)