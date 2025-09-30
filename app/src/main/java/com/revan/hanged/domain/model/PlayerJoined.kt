package com.revan.hanged.domain.model

data class PlayerJoined(
    val userId: String,
    val username: String,
)

fun PlayerJoined.toPlayer () : Player {
    return Player (
        eliminated = false,
        id = userId,
        name = username,
        score = 0,
        ready = false
    )
}
