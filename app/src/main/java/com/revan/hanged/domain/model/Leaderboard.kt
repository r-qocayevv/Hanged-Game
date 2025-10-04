package com.revan.hanged.domain.model

data class Leaderboard (
    val rank: Int,
    val totalScore: Int,
    val userId: String,
    val username: String
)