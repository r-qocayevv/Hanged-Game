package com.revan.hanged.presentation.game_history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revan.hanged.domain.model.Game
import com.revan.hanged.domain.use_case.GetGamesUseCase
import com.revan.hanged.domain.use_case.GetLeaderboardUseCase
import com.revan.hanged.domain.use_case.GetUserDetailUseCase
import com.revan.hanged.domain.use_case.GetUserIdFromLocalUseCase
import com.revan.hanged.domain.use_case.GetUsernameFromLocalUseCase
import com.revan.hanged.navigation.NavigationCommand
import com.revan.hanged.navigation.Navigator
import com.revan.hanged.utils.Toaster
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class GameHistoryViewModel @Inject constructor(
    private val navigator: Navigator,
    private val toaster: Toaster,
    private val getUserIdFromLocalUseCase: GetUserIdFromLocalUseCase,
    private val getUsernameFromLocalUseCase: GetUsernameFromLocalUseCase,
    private val getGamesUseCase: GetGamesUseCase,
    private val getLeaderboardUseCase: GetLeaderboardUseCase,
    private val getUserDetailUseCase: GetUserDetailUseCase

) : ViewModel() {

    private val _state: MutableStateFlow<GameHistoryState> = MutableStateFlow(GameHistoryState())
    val state = _state.asStateFlow()

    init {
        getMyOwnId()
        getMyOwnName()
        getGames()
    }

    private fun getMyOwnName() {
        viewModelScope.launch {
            getUsernameFromLocalUseCase().collect { myUsername ->
                myUsername?.let { safeUsername ->
                    _state.update {
                        it.copy(
                            myUsername = safeUsername
                        )
                    }
                }
            }
        }
    }

    private fun getMyOwnId() {
        viewModelScope.launch {
            getUserIdFromLocalUseCase().collect { myId ->
                myId?.let { safeId ->
                    getMyOwnDetails(safeId)
                    getLeaderboard(safeId)
                    _state.update {
                        it.copy(
                            myId = safeId
                        )
                    }

                }
            }
        }
    }

    private fun getMyOwnDetails(id: String) {
        _state.update {
            it.copy(
                isLoading = true
            )
        }
        viewModelScope.launch {
            try {
                val user = getUserDetailUseCase(id)
                _state.update {
                    it.copy(
                        myOwnDetails = user,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                toaster.emitToastMessage(message = e.localizedMessage ?: "Unknown error")
            }
        }
    }


    private fun getLeaderboard(myId: String) {
        viewModelScope.launch {
            try {
                val leaderboard = getLeaderboardUseCase()
                val myRank = leaderboard.find { it.userId == myId }
                _state.update {
                    it.copy(
                        leaderboard = leaderboard,
                        myRank = myRank
                    )
                }
            } catch (e: Exception) {
                toaster.emitToastMessage(message = e.localizedMessage ?: "Unknown error")
            }
        }
    }


    fun onEvent(event: GameHistoryEvent) {
        when (event) {
            GameHistoryEvent.ToBack -> {
                popBackStack()
            }

            is GameHistoryEvent.OnTabSelected -> {
                updateSelectedTabIndex(event.index)
            }

            is GameHistoryEvent.OnGameSelected -> {
                onGameSelected(event.game)
            }

            GameHistoryEvent.CloseGameDetailBottomSheet -> {
                closeGameDetailBottomSheet()
            }

            GameHistoryEvent.ChangeWordVisibility -> {
                changeWordVisibility()
            }
        }
    }

    private fun changeWordVisibility() {
        _state.update {
            it.copy(
                isWordVisible = !_state.value.isWordVisible
            )
        }
    }

    private fun closeGameDetailBottomSheet() {
        _state.update {
            it.copy(
                isGameDetailBottomSheetOpen = false
            )
        }
    }

    private fun onGameSelected(game: Game) {
        val eliminatedPlayers = game.players.filter { it.eliminated }
        val activePlayers = game.players.filter { !it.eliminated }
        _state.update {
            it.copy(
                selectedGame = game,
                eliminatedPlayers = eliminatedPlayers,
                activePlayer = activePlayers,
                isGameDetailBottomSheetOpen = true
            )
        }
    }

    private fun updateSelectedTabIndex(index: Int) {
        _state.update {
            it.copy(
                selectedTabIndex = index
            )
        }
    }

    private fun getGames() {
        viewModelScope.launch {
            try {
                val games = getGamesUseCase()
                _state.update {
                    it.copy(
                        games = games
                    )
                }
            } catch (e: Exception) {
                toaster.emitToastMessage(message = e.localizedMessage ?: "Unknown error")
            }
        }
    }

    private fun popBackStack() {
        viewModelScope.launch {
            navigator.sendNavigation(NavigationCommand.ToBack)
        }
    }

}