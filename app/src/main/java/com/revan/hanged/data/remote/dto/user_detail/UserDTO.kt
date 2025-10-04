package com.revan.hanged.data.remote.dto.user_detail


import com.google.gson.annotations.SerializedName
import com.revan.hanged.domain.model.User

data class UserDTO(
    @SerializedName("losses")
    val losses: Int,
    @SerializedName("totalGames")
    val totalGames: Int,
    @SerializedName("totalScore")
    val totalScore: Int,
    @SerializedName("userId")
    val userId: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("wins")
    val wins: Int
)

fun UserDTO.toUser(): User {
    return User(
        losses = losses,
        totalGames = totalGames,
        totalScore = totalScore,
        userId = userId,
        username = username,
        wins = wins
    )

}