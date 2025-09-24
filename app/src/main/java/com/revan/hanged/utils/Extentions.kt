package com.revan.hanged.utils

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.firebase.Timestamp
import com.revan.hanged.domain.RoomStatus
import org.w3c.dom.Text
import java.util.concurrent.TimeUnit


fun Modifier.clickWithoutRipple (onClick : () -> Unit) : Modifier {
    return this.clickable (
        indication = null,
        interactionSource = null
    ){
        onClick()
    }
}



fun Timestamp.getTimeAgo(): String {
    val timestampMillis = this.toDate().time  // convert Timestamp to milliseconds
    val now = System.currentTimeMillis()
    val diff = now - timestampMillis

    val seconds = TimeUnit.MILLISECONDS.toSeconds(diff)
    val minutes = TimeUnit.MILLISECONDS.toMinutes(diff)
    val hours = TimeUnit.MILLISECONDS.toHours(diff)
    val days = TimeUnit.MILLISECONDS.toDays(diff)

    return when {
        seconds < 60 -> "just now"
        minutes < 60 -> "$minutes minutes ago"
        hours < 24 -> "$hours hours ago"
        days < 7 -> "$days days ago"
        else -> {
            val weeks = days / 7
            "$weeks weeks ago"
        }
    }
}

fun String.toRoomStatus () : RoomStatus {
    try {
        return when (this) {
            "waiting" -> {
                RoomStatus.WAITING
            }

            "game" -> {
                RoomStatus.PLAYING
            }

            "full" -> {
                RoomStatus.FULL
            }

            else -> {
                RoomStatus.WAITING
            }
        }
    } catch (e : Exception) {
        e.printStackTrace()
        return RoomStatus.WAITING
    }
}

fun String.firstCharToUpperCase () : String {
    return this.replaceFirstChar { it.uppercase() }
}
