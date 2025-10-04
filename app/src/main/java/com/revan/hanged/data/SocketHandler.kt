package com.revan.hanged.data

import android.util.Log
import com.revan.hanged.domain.model.RoomState
import com.revan.hanged.domain.model.GameOver
import com.revan.hanged.domain.model.GameInfo
import com.revan.hanged.domain.model.GameSettingsUpdate
import com.revan.hanged.domain.model.GameUpdate
import com.revan.hanged.domain.model.PlayerJoined
import com.revan.hanged.domain.model.PlayerLeft
import com.revan.hanged.domain.model.PlayerReadyUpdated
import com.revan.hanged.domain.model.RoomInfo
import com.revan.hanged.domain.model.RoomUpdate
import com.revan.hanged.domain.model.Turn
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
) {

    private val scope = CoroutineScope(Dispatchers.IO)

    private val _homeSocketEvents = MutableSharedFlow<HomeSocketEvents>()
    val homeSocketEvents = _homeSocketEvents.asSharedFlow()

    private val _gameSocketEvents = MutableSharedFlow<GameSocketEvents>()
    val gameSocketEvents = _gameSocketEvents.asSharedFlow()

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
                _homeSocketEvents.emit(HomeSocketEvents.RoomUpdateEvent(roomUpdate))
            }
        }

        socketService.on("playerJoined") { args ->
            val dataJson = args.firstOrNull()?.toString() ?: return@on
            val playerJoined: PlayerJoined? = JsonParser.parseFromJson(dataJson)
            Log.d("SOCKET_EVENT", "PlayerJoined → $playerJoined")
            scope.launch {
                _gameSocketEvents.emit(GameSocketEvents.PlayerJoinedEvent(playerJoined))
            }
        }

        socketService.on("playerReadyUpdated") { args ->
            val dataJson = args.firstOrNull()?.toString() ?: return@on
            val playerReadyUpdated: PlayerReadyUpdated? = JsonParser.parseFromJson(dataJson)
            Log.d("SOCKET_EVENT", "PlayerReadyUpdated → $playerReadyUpdated")
            scope.launch {
                _gameSocketEvents.emit(GameSocketEvents.PlayerReadyUpdatedEvent(playerReadyUpdated))
            }
        }

        socketService.on("playerLeft") { args ->
            val dataJson = args.firstOrNull()?.toString() ?: return@on
            val userId: PlayerLeft? = JsonParser.parseFromJson(dataJson)
            Log.d("SOCKET_EVENT", "PlayerLeft → $userId")
            scope.launch {
                _gameSocketEvents.emit(GameSocketEvents.PlayerLeftEvent(userId?.userId))
            }
        }

        socketService.on("gameStarted") { args ->
            val dataJson = args.firstOrNull()?.toString() ?: return@on
            val gameInfo: GameInfo? = JsonParser.parseFromJson(dataJson)
            Log.d("SOCKET_EVENT", "GameInfo → $gameInfo")
            scope.launch {
                _gameSocketEvents.emit(GameSocketEvents.GameStartedEvent(gameInfo))
            }
        }

        socketService.on("turn") { args ->
            val dataJson = args.firstOrNull()?.toString() ?: return@on
            val turn: Turn? = JsonParser.parseFromJson(dataJson)
            Log.d("SOCKET_EVENT", "Turn → $turn")
            scope.launch {
                _gameSocketEvents.emit(GameSocketEvents.TurnEvent(turn))
            }
        }

        socketService.on("roomState") {args ->
            val dataJson = args.firstOrNull()?.toString() ?: return@on
            println("ROOM STATE : $dataJson")
            val roomState : RoomState? = JsonParser.parseFromJson(dataJson)
            Log.d("SOCKET_EVENT", "GameUpdate → $roomState")
            scope.launch {
                _gameSocketEvents.emit(GameSocketEvents.RoomStateEvent(roomState = roomState))
            }
        }

        socketService.on("gameUpdate") { args ->
            val dataJson = args.firstOrNull()?.toString() ?: return@on
            val discovered: GameUpdate? = JsonParser.parseFromJson(dataJson)
            Log.d("SOCKET_EVENT", "GameUpdate → $discovered")
            scope.launch {
                _gameSocketEvents.emit(GameSocketEvents.GameUpdateEvent(discovered))
            }
        }

        socketService.on("gameSettingsUpdate") { args ->
            val dataJson = args.firstOrNull()?.toString() ?: return@on
            val gameSettingsUpdate: GameSettingsUpdate? = JsonParser.parseFromJson(dataJson)
            Log.d("SOCKET_EVENT", "GameSettingsUpdate → $gameSettingsUpdate")
            scope.launch {
                _gameSocketEvents.emit(GameSocketEvents.GameSettingsUpdateEvent(gameSettingsUpdate))
            }
        }

        socketService.on("playerEliminated") { args ->
            val dataJson = args.firstOrNull()?.toString() ?: return@on
            val userId: PlayerLeft? = JsonParser.parseFromJson(dataJson)
            Log.d("SOCKET_EVENT", "PlayerEliminated → $userId")
            scope.launch {
                _gameSocketEvents.emit(GameSocketEvents.PlayerEliminatedEvent(userId?.userId))
            }
        }

        socketService.on("gameOver") { args ->
            val dataJson = args.firstOrNull()?.toString() ?: return@on
            val gameOver: GameOver? = JsonParser.parseFromJson(dataJson)
            Log.d("SOCKET_EVENT", "GameOver → $gameOver")
            scope.launch {
                _gameSocketEvents.emit(GameSocketEvents.GameOverEvent(gameOver))
            }
        }
    }




        fun joinRoom(roomInfo: RoomInfo) {
            val messageData = JSONObject()

            messageData.apply {
                put("roomId", roomInfo.roomId)
                put("userId", roomInfo.userId)
                put("username", roomInfo.userName)
                put("difficulty", roomInfo.difficulty)
                put("language", roomInfo.gameLanguage.language)

            }
            socketService.emit("joinRoom", messageData)
        }

        fun ready(roomInfo: RoomInfo) {
            val readyData = JSONObject()

            readyData.apply {
                put("roomId", roomInfo.roomId)
                put("userId", roomInfo.userId)
                put("username", roomInfo.userName)
                put("difficulty", roomInfo.difficulty)
                put("language", roomInfo.gameLanguage.language)
            }
            socketService.emit("ready", readyData)
        }

        fun unready(roomInfo: RoomInfo) {
            val unready = JSONObject()

            unready.apply {
                put("roomId", roomInfo.roomId)
                put("userId", roomInfo.userId)
            }

            socketService.emit("unready", unready)
        }

        fun leaveRoom(leave: RoomInfo) {
            val leaveData = JSONObject()
            leaveData.apply {
                put("roomId", leave.roomId)
                put("userId", leave.userId)
            }
            socketService.emit("leaveRoom", leaveData)
        }

        fun guessLetter(roomInfo: RoomInfo, letter: String) {
            val guessData = JSONObject()
            guessData.apply {
                put("roomId", roomInfo.roomId)
                put("userId", roomInfo.userId)
                put("letter", letter)
            }
            socketService.emit("guessLetter", guessData)
        }

        fun guessWord(roomInfo: RoomInfo, word: String) {
            val guessData = JSONObject()
            guessData.apply {
                put("roomId", roomInfo.roomId)
                put("userId", roomInfo.userId)
                put("guess", word.lowercase())
            }
            socketService.emit("guessWord", guessData)

        }


    }