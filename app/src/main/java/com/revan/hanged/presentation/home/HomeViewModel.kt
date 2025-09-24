package com.revan.hanged.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revan.hanged.data.SocketHandler
import com.revan.hanged.domain.RoomStatus
import com.revan.hanged.domain.model.JoinRoomInfo
import com.revan.hanged.domain.model.Room
import com.revan.hanged.domain.model.RoomUpdate
import com.revan.hanged.domain.model.RoomUpdateType
import com.revan.hanged.domain.use_case.GetGamesUseCase
import com.revan.hanged.domain.use_case.GetRoomsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRoomsUseCase: GetRoomsUseCase,
    private val getGamesUseCase: GetGamesUseCase,
    private val socketHandler: SocketHandler
) : ViewModel() {

    init {
        getRooms()
        getGames()
        observeRoomUpdate()
    }

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.ShowRoomStatusGuide -> {
                showRoomStatusGuide(event.showBottomSheet)
            }

            is HomeEvent.JoinRoom -> {
                val room = event.room
                val userId = event.userId
                val username = event.username

                val joinRoomInfo = JoinRoomInfo(
                    roomId = room.roomId,
                    userId = userId,
                    userName = username,
                    difficulty = room.difficulty,
                    language = room.language
                )
                if (room.status != RoomStatus.FULL) {
                    joinRoom(joinRoomInfo)
                }
            }
        }
    }


    fun getRooms() {
        viewModelScope.launch {
            val rooms = getRoomsUseCase()
            _state.update {
                it.copy(
                    rooms = rooms,
                    availableRoomCount = rooms.size
                )
            }
        }

    }

    fun getGames() {
        viewModelScope.launch {
            val games = getGamesUseCase()
            _state.update {
                it.copy(
                    games = games
                )
            }
        }
    }

    fun joinRoom(roomInfo: JoinRoomInfo) {
        viewModelScope.launch {
            socketHandler.joinRoom(roomInfo)
        }
    }

    fun observeRoomUpdate() {
        viewModelScope.launch {
            socketHandler.roomUpdate.collect { roomUpdate ->
                roomUpdate?.let { safeRoomUpdate ->
                    updateRoomState(safeRoomUpdate)
                }
            }
        }
    }

    private fun updateRoomState(roomUpdate: RoomUpdate) {
        val updatedList = _state.value.rooms.map { currentRoom ->
            if (currentRoom.roomId == roomUpdate.roomId) {
                val room = updateRoomPlayerCount(roomUpdate, currentRoom)
                val roomStatus =
                    if (room.playerCount == room.maxPlayers) RoomStatus.FULL else room.status
                room.copy(
                    status = roomStatus
                )

            } else {
                currentRoom
            }

        }
        _state.update {
            it.copy(
                rooms = updatedList
            )
        }
    }

    private fun updateRoomPlayerCount(roomUpdate: RoomUpdate, room: Room): Room {
        return when (roomUpdate.type) {
            RoomUpdateType.JOIN -> {
                if (room.playerCount < room.maxPlayers) {
                    room.copy(
                        playerCount = room.playerCount + 1,
                    )
                } else {
                    room
                }
            }

            RoomUpdateType.LEAVE -> {
                if (room.playerCount > 0) {
                    room.copy(
                        playerCount = room.playerCount - 1,
                    )
                } else {
                    room
                }
            }
        }
    }

    private fun showRoomStatusGuide(showBottomSheet: Boolean) {
        _state.update {
            it.copy(
                isVisibleRoomStatusGuide = showBottomSheet

            )
        }
    }
}