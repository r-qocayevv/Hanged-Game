package com.revan.hanged.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.firebase.Timestamp
import com.revan.hanged.domain.RoomStatus
import com.revan.hanged.navigation.ScreenRoute
import com.revan.hanged.ui.theme.DarkGray
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.concurrent.TimeUnit


fun Modifier.clickWithoutRipple(onClick: () -> Unit, isEnabled: Boolean = true): Modifier {
    return this.clickable(
        enabled = isEnabled,
        indication = null,
        interactionSource = null
    ) {
        onClick()
    }
}


fun NavController.safePopBackStack() {
    if (this.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED) {
        this.popBackStack()
    }
}

@Composable
fun Modifier.clickWithDarkGrayRipple(
    onClick: () -> Unit,
    isButtonEnabled: Boolean = true
): Modifier {
    return this.clickable(
        enabled = isButtonEnabled,
        interactionSource = remember { MutableInteractionSource() },
        indication = ripple(color = DarkGray),
        onClick = {
            onClick()
        }
    )
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

fun String.getTimeAgo(): String {
    val timestampMillis = Instant.parse(this).toEpochMilli()
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

fun String.formatIsoToCustom(): String {
    val instant = Instant.parse(this)
    val formatter = DateTimeFormatter.ofPattern("MMM dd, HH:mm", Locale.ENGLISH)
        .withZone(ZoneId.systemDefault())

    return formatter.format(instant)
}

fun String.toRoomStatus(): RoomStatus {
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
    } catch (e: Exception) {
        e.printStackTrace()
        return RoomStatus.WAITING
    }
}

fun String.isValidPassword(): Boolean {
    return this.length >= 8 && this.any { it.isDigit() }
}

fun String.isValidEmail(): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.firstCharToUpperCase(): String {
    return this.replaceFirstChar { it.uppercase() }
}

fun NavHostController.safeNavigate(route: ScreenRoute, popUpTo: ScreenRoute?) {
    this.navigate(route) {
        popUpTo?.let {
            this.popUpTo(it) {
                inclusive = true
            }
        }
    }
}
