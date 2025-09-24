package com.revan.hanged.domain.model

import com.google.gson.annotations.SerializedName

data class RoomUpdate(
    val roomId: String,
    val userId: String,
    val type: RoomUpdateType
)

enum class RoomUpdateType {

    @SerializedName("join")
    JOIN,

    @SerializedName("leave")
    LEAVE,
}
