package com.revan.hanged.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.revan.hanged.domain.model.Player

data class PlayerDTO(
    @SerializedName("eliminated")
    val eliminated: Boolean = false,
    @SerializedName("id")
    val id: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("score")
    val score: Int = 0
)

fun PlayerDTO.toPlayer() : Player {
    return Player (
        eliminated = eliminated,
        id = id,
        name = name,
        score = score
    )

}
