package com.revan.hanged.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revan.hanged.data.SocketEvents
import com.revan.hanged.data.SocketHandler
import com.revan.hanged.domain.RoomStatus
import com.revan.hanged.domain.model.JoinRoomInfo
import com.revan.hanged.domain.model.Room
import com.revan.hanged.domain.model.RoomUpdate
import com.revan.hanged.domain.model.RoomUpdateType
import com.revan.hanged.domain.use_case.GetGamesUseCase
import com.revan.hanged.domain.use_case.GetRoomsUseCase
import com.revan.hanged.domain.use_case.LogoutUseCase
import com.revan.hanged.navigation.NavigationCommand
import com.revan.hanged.navigation.Navigator
import com.revan.hanged.navigation.ScreenRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val navigator: Navigator,
    private val getRoomsUseCase: GetRoomsUseCase,
    private val getGamesUseCase: GetGamesUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val socketHandler: SocketHandler
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()


    init {
        getRooms()
        getGames()
        observeRoomUpdate()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.ShowRoomStatusGuide -> {
                showRoomStatusGuide(event.showBottomSheet)
            }

            is HomeEvent.JoinRoom -> {
                joinRoom(roomInfo = event.roomInfo)
            }

            is HomeEvent.OnNavigate -> {
                navigate(event.route)
            }

            HomeEvent.LogOut -> {
                logoutUseCase()
                navigate(ScreenRoute.Login)
            }
        }
    }

    private fun navigate(route: ScreenRoute) {
        viewModelScope.launch {
            navigator.sendNavigation(NavigationCommand.OnNavigate(route = route))
        }
    }


    fun getRooms() {
        _state.update {
            it.copy(
                isLoading = true
            )
        }
        viewModelScope.launch {
            val rooms = getRoomsUseCase()
            _state.update {
                it.copy(
                    rooms = rooms,
                    availableRoomCount = rooms.size,
                    isLoading = false
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
            socketHandler.socketEvents.collect { event ->
                when(event) {
                    is SocketEvents.RoomUpdateEvent -> {
                        event.roomUpdate?.let { safeRoomUpdate ->
                            updateRoomState(safeRoomUpdate)
                        }
                    }
                }
            }
        }
    }

    private fun updateRoomState(roomUpdate: RoomUpdate) {
        val updatedList = state.value.rooms.map { currentRoom ->
            if (currentRoom.roomId == roomUpdate.roomId) {
                val room = updateRoomPlayerCount(roomUpdate, currentRoom)
                val roomStatus = if (room.playerCount == room.maxPlayers) RoomStatus.FULL else room.status
                room.copy(status = roomStatus)
            } else {
                currentRoom
            }
        }
        _state.update {
            it.copy(rooms = updatedList)
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
        _state.update { it.copy(isVisibleRoomStatusGuide = showBottomSheet) }
    }
}