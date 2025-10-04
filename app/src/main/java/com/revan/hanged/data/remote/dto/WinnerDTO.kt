package com.revan.hanged.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.revan.hanged.domain.model.Winner

data class WinnerDTO(
    @SerializedName("id")
    val id: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("score")
    val score: Int = 0
)

fun WinnerDTO.toWinner() : Winner {
    return Winner (
        id = id,
        name = name,
        score = score
    )
}