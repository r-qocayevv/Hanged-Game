package com.revan.hanged.domain.model

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.revan.hanged.domain.GameLanguage
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class RoomInfo(
    val roomId: String,
    val userId: String,
    val userName: String,
    val difficulty: String,
    val gameLanguage: GameLanguage,
    val maxPlayerCount: Int = 0,
    val currentPlayerCount: Int = 0
)

val CustomNavTypeRoomInfo =
    object : NavType<RoomInfo>(isNullableAllowed = true) {
        override fun get(bundle: Bundle, key: String): RoomInfo? {
            return bundle.getString(key)?.let { Json.decodeFromString(it) }
        }

        override fun parseValue(value: String): RoomInfo {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: RoomInfo): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: Bundle, key: String, value: RoomInfo) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }
