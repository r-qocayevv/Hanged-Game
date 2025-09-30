package com.revan.hanged.data

import com.revan.hanged.domain.model.RoomUpdate

sealed class HomeSocketEvents {
    data class RoomUpdateEvent(val roomUpdate: RoomUpdate?) : HomeSocketEvents()
}