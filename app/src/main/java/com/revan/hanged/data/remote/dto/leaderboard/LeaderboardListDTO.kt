package com.revan.hanged.data.remote.dto.leaderboard


import com.google.gson.annotations.SerializedName

data class LeaderboardListDTO(
    @SerializedName("data")
    val `data`: LeaderboardDataDTO,
    @SerializedName("success")
    val success: Boolean
)