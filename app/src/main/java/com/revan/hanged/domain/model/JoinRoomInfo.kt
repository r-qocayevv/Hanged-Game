package com.revan.hanged.domain.model

data class JoinRoomInfo(
    val roomId: String,
    val userId: String,
    val userName: String,
    val difficulty: String,
    val language: String
)
