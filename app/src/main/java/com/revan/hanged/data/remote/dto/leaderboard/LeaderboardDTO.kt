package com.revan.hanged.data.remote.dto.leaderboard


import com.google.gson.annotations.SerializedName
import com.revan.hanged.domain.model.Leaderboard

data class LeaderboardDTO(
    @SerializedName("rank")
    val rank: Int,
    @SerializedName("totalScore")
    val totalScore: Int,
    @SerializedName("userId")
    val userId: String,
    @SerializedName("username")
    val username: String
)


fun LeaderboardDTO.toLeaderboard(): Leaderboard {
    return Leaderboard(
        rank = rank,
        totalScore = totalScore,
        userId = userId,
        username = username
    )
}