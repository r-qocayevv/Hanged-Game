package com.revan.hanged.data.remote.dto.user_detail


import com.google.gson.annotations.SerializedName

data class UserDetailDTO(
    @SerializedName("data")
    val `data`: UserDataDTO,
    @SerializedName("success")
    val success: Boolean
)