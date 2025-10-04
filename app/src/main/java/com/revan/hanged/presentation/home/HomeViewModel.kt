package com.revan.hanged.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revan.hanged.data.HomeSocketEvents
import com.revan.hanged.data.SocketHandler
import com.revan.hanged.domain.RoomStatus
import com.revan.hanged.domain.model.Room
import com.revan.hanged.domain.model.RoomInfo
import com.revan.hanged.domain.model.RoomUpdate
import com.revan.hanged.domain.model.RoomUpdateType
import com.revan.hanged.domain.use_case.GetRoomsUseCase
import com.revan.hanged.domain.use_case.GetUserIdFromLocalUseCase
import com.revan.hanged.domain.use_case.GetUsernameFromLocalUseCase
import com.revan.hanged.domain.use_case.LogoutUseCase
import com.revan.hanged.domain.use_case.SaveLoginStateUseCase
import com.revan.hanged.navigation.NavigationCommand
import com.revan.hanged.navigation.Navigator
import com.revan.hanged.navigation.ScreenRoute
import com.revan.hanged.navigation.ScreenRoute.Home
import com.revan.hanged.navigation.ScreenRoute.Login
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
    private val logoutUseCase: LogoutUseCase,
    private val saveLoginStateUseCase: SaveLoginStateUseCase,
    private val getUsernameFromLocalUseCase: GetUsernameFromLocalUseCase,
    private val getUserIdFromLocalUseCase: GetUserIdFromLocalUseCase,
    private val socketHandler: SocketHandler
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()


    init {
        getUserDataFromLocal()
        getRooms()
        observeRoomUpdate()
    }

    private fun getUserDataFromLocal() {
        viewModelScope.launch {
            getUsernameFromLocalUseCase().collect { username ->
                username?.let { safeUsername ->
                    _state.update {
                        it.copy(
                            username = safeUsername
                        )
                    }
                }
            }
        }

        viewModelScope.launch {
            getUserIdFromLocalUseCase().collect { userId ->
                userId?.let { safeUserId ->
                    _state.update {
                        it.copy(
                            userId = safeUserId
                        )
                    }
                }
            }
        }
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

            is HomeEvent.LogOut -> {
                viewModelScope.launch {
                    logoutUseCase()
                    saveLoginStateUseCase(false)
                }
                navigate(
                    route = Login,
                    popUpTo = Home(username = event.username)
                )
            }

            HomeEvent.RefreshPage -> {
                refreshPage()
            }

            is HomeEvent.OnItemSelection ->{
                onItemSelection(event.tabIndex)
            }
        }
    }

    private fun onItemSelection(tabIndex: Int) {
        _state.update {
            it.copy(
                selectedTabIndex = tabIndex
            )
        }
        updateFilteredRooms()
    }

    private fun refreshPage() {
        getRooms()
    }

    private fun navigate(route: ScreenRoute, popUpTo: ScreenRoute? = null) {
        viewModelScope.launch {
            navigator.sendNavigation(NavigationCommand.OnNavigate(route = route, popUpTo = popUpTo))
        }
    }


    fun getRooms() {
        _state.update {
            it.copy(
                isLoading = true,
                isRefreshing = true
            )
        }
        viewModelScope.launch {
            val rooms = getRoomsUseCase()

            _state.update {
                it.copy(
                    rooms = rooms,
                    availableRoomCount = rooms.size,
                    isLoading = false,
                    isRefreshing = false
                )
            }

            updateFilteredRooms()
        }

    }

    private fun updateFilteredRooms () {
        val selectedRoomStatus = when (state.value.selectedTabIndex) {
            0 -> RoomStatus.WAITING
            1 -> RoomStatus.PLAYING
            2 -> RoomStatus.FULL
            else -> RoomStatus.WAITING
        }

        val rooms = _state.value.rooms

        println("ROOMS : $rooms")
        val filteredRooms = rooms.filter { room ->
            room.status == selectedRoomStatus
        }

        _state.update {
            it.copy(
                filteredRooms = filteredRooms
            )
        }
    }


    fun joinRoom(roomInfo: RoomInfo) {
        viewModelScope.launch {
            socketHandler.joinRoom(roomInfo)
            navigate(ScreenRoute.Game(roomInfo = roomInfo))
        }
    }

    fun observeRoomUpdate() {
        viewModelScope.launch {
            socketHandler.homeSocketEvents.collect { event ->
                when (event) {
                    is HomeSocketEvents.RoomUpdateEvent -> {
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
                val roomStatus =
                    if (room.playerCount == room.maxPlayers) RoomStatus.FULL else room.status
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