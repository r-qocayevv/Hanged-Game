package com.revan.hanged.domain.model

data class Player(
    val eliminated: Boolean,
    val id: String,
    val name: String,
    val score: Int,
    val ready: Boolean = true,
)
