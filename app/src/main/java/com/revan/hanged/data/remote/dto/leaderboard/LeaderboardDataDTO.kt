package com.revan.hanged.data.remote.dto.leaderboard

import com.google.gson.annotations.SerializedName

data class LeaderboardDataDTO(
    @SerializedName("leaderboard")
    val leaderboard: List<LeaderboardDTO>
)