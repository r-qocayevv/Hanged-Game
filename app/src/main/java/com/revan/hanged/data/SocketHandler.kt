package com.revan.hanged.data

import android.util.Log
import com.revan.hanged.domain.model.JoinRoomInfo
import com.revan.hanged.domain.model.RoomUpdate
import com.revan.hanged.utils.JsonParser
import io.socket.client.Socket
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

class SocketHandler @Inject constructor(
    private val socketService: Socket
){

    private val scope = CoroutineScope(Dispatchers.IO)

    private val _socketEvents = MutableSharedFlow<SocketEvents>()
    val socketEvents = _socketEvents.asSharedFlow()

    init {
        socketService.connect()
        observeSocket()
    }

    fun observeSocket() {
        socketService.on(Socket.EVENT_CONNECT) {
            Log.d("SOCKET_EVENT", "SOCKET CONNECT")
        }

        socketService.on(Socket.EVENT_CONNECT_ERROR) {
            Log.e("SOCKET_EVENT", "CONNECT ERROR: ${it.getOrNull(0)}")
        }

        socketService.on("roomUpdate") { args ->
            val dataJson = args.firstOrNull()?.toString() ?: return@on
            val roomUpdate: RoomUpdate? = JsonParser.parseFromJson(dataJson)
            Log.d("SOCKET_EVENT", "RoomUpdate → $roomUpdate")
            scope.launch {
                _socketEvents.emit(SocketEvents.RoomUpdateEvent(roomUpdate))
            }
        }
    }

    fun joinRoom(joinRoomInfo : JoinRoomInfo) {
        val messageData = JSONObject()

        messageData.apply {
            put("roomId", joinRoomInfo.roomId)
            put("userId", joinRoomInfo.userId)
            put("userName", joinRoomInfo.userName)
            put("difficulty", joinRoomInfo.difficulty)
            put("language", joinRoomInfo.language)
        }

        socketService.emit("joinRoom",messageData)
    }
}