package com.revan.hanged.domain.model

import com.google.gson.annotations.SerializedName

data class Player(
    val eliminated: Boolean,
    val id: String,
    val name: String,
    val score: Int
)
