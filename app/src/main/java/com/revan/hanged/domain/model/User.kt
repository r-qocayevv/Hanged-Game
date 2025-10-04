package com.revan.hanged.domain.model


data class User(
    val losses: Int,
    val totalGames: Int,
    val totalScore: Int,
    val userId: String,
    val username: String,
    val wins: Int
)
