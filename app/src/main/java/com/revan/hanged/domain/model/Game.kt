package com.revan.hanged.domain.model

import com.google.firebase.Timestamp

data class Game(
    val createdAt: String,
    val difficulty: String,
    val language: String,
    val players: List<Player>,
    val roomId: String,
    val roomName: String,
    val winner: Winner?,
    val word: String
)
