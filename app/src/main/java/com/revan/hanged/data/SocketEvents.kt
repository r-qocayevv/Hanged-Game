package com.revan.hanged.data

import com.revan.hanged.domain.model.RoomUpdate

sealed class SocketEvents {
    data class RoomUpdateEvent(val roomUpdate: RoomUpdate?) : SocketEvents()
}