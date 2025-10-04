package com.revan.hanged.data.remote.dto.user_detail


import com.google.gson.annotations.SerializedName

data class UserDataDTO(
    @SerializedName("user")
    val user: UserDTO?,
    @SerializedName("message")
    val message : String?
)