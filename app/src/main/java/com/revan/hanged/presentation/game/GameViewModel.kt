package com.revan.hanged.presentation.game

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revan.hanged.data.GameSocketEvents
import com.revan.hanged.data.SocketHandler
import com.revan.hanged.domain.GameResult
import com.revan.hanged.domain.model.GameInfo
import com.revan.hanged.domain.model.GameOver
import com.revan.hanged.domain.model.GameUpdate
import com.revan.hanged.domain.model.PlayerJoined
import com.revan.hanged.domain.model.PlayerReadyUpdated
import com.revan.hanged.domain.model.RoomInfo
import com.revan.hanged.domain.model.RoomState
import com.revan.hanged.domain.model.Turn
import com.revan.hanged.domain.model.toPlayer
import com.revan.hanged.domain.use_case.GetUserIdFromLocalUseCase
import com.revan.hanged.domain.use_case.GetUsernameFromLocalUseCase
import com.revan.hanged.navigation.NavigationCommand
import com.revan.hanged.navigation.Navigator
import com.revan.hanged.navigation.ScreenRoute
import com.revan.hanged.ui.theme.Red
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val socketHandler: SocketHandler,
    private val navigator: Navigator,
    private val getUsernameFromLocalUseCase: GetUsernameFromLocalUseCase,
    private val getUserIdFromLocalUseCase: GetUserIdFromLocalUseCase
) : ViewModel() {


    private val _state = MutableStateFlow(GameState())
    val state = _state.asStateFlow()

    private var timerJob: Job? = null

    init {
        observeGameUpdate()
        getUserNameFromLocal()
        getUserIdFromLocal()
    }

    fun onEvent(event: GameEvent) {
        when (event) {
            is GameEvent.ChangeReadyState -> {
                changeReadyState(event.roomInfo)
            }

            is GameEvent.LeaveRoom -> {
                leaveRoom(event.roomInfo)
            }

            is GameEvent.OnChangeGuessWord -> {
                changeGuessWord(event.newGuessWord)
            }

            GameEvent.OnOpenGuessWord -> {
                onOpenGuessWord()
            }

            is GameEvent.KeyClicked -> {
                guessLetter(key = event.key, roomInfo = event.roomInfo)
            }

            is GameEvent.OnGuessWord -> {
                onGuessWord(roomInfo = event.roomInfo)
            }

            GameEvent.ChangePlayerBottomSheetState -> {
                changePlayerBottomSheetState()
            }

            GameEvent.ChangeConfirmExitBottomSheetState -> {
                changeConfirmExitBottomSheetState()
            }
        }
    }

    private fun changeConfirmExitBottomSheetState() {
        _state.update {
            it.copy(
                isConfirmExitBottomSheetOpen = !_state.value.isConfirmExitBottomSheetOpen
            )
        }
    }

    private fun changePlayerBottomSheetState() {
        _state.update {
            it.copy(
                isPlayerBottomSheetOpen = !_state.value.isPlayerBottomSheetOpen
            )
        }
    }

    private fun onGuessWord(roomInfo: RoomInfo) {
        val guessedWord = _state.value.guessWord
        socketHandler.guessWord(roomInfo = roomInfo, word = guessedWord)
    }

    private fun getUserIdFromLocal() {
        viewModelScope.launch {
            getUserIdFromLocalUseCase().collect { userId ->
                _state.update {
                    it.copy(
                        userId = userId
                    )
                }
            }
        }
    }

    private fun getUserNameFromLocal() {
        viewModelScope.launch {
            getUsernameFromLocalUseCase().collect { username ->
                _state.update {
                    it.copy(username = username)
                }
            }
        }
    }

    private fun navigate(route: ScreenRoute, popUpTo: ScreenRoute? = null) {
        viewModelScope.launch {
            navigator.sendNavigation(NavigationCommand.OnNavigate(route = route, popUpTo = popUpTo))
        }
    }

    private fun popBackStack() {
        viewModelScope.launch {
            navigator.sendNavigation(NavigationCommand.ToBack)

        }
    }

    private fun guessLetter(key: Char, roomInfo: RoomInfo) {
        socketHandler.guessLetter(roomInfo = roomInfo, letter = key.toString())
    }

    private fun onOpenGuessWord() {
        println("onOpenGuessWord called ${_state.value.isGuessWordOpen}")
        _state.update {
            it.copy(
                isGuessWordOpen = !it.isGuessWordOpen
            )
        }
    }

    private fun changeGuessWord(newGuessWord: String) {
        _state.update {
            it.copy(
                guessWord = newGuessWord
            )
        }
    }

    private fun leaveRoom(roomInfo: RoomInfo) {

        _state.update {
            it.copy(isGameFinished = false, isConfirmExitBottomSheetOpen = false)
        }
        socketHandler.leaveRoom(leave = roomInfo)
        popBackStack()

    }

    private fun changeReadyState(roomInfo: RoomInfo) {
        if (_state.value.isReady) {
            socketHandler.unready(roomInfo = roomInfo)
            _state.update {
                it.copy(
                    isReady = false
                )
            }
        } else {
            socketHandler.ready(roomInfo = roomInfo)
            _state.update {
                it.copy(
                    isReady = true
                )
            }
        }
    }


    fun observeGameUpdate() {
        viewModelScope.launch {
            socketHandler.gameSocketEvents.collect { event ->
                when (event) {
                    is GameSocketEvents.GameOverEvent -> {
                        event.gameOver?.let { safeGameOver ->
                            gameOver(safeGameOver)
                        }
                    }

                    is GameSocketEvents.GameSettingsUpdateEvent -> {
                        event.gameSettingsUpdate?.canGuessWord?.let { safeCanGuessWord ->
                            updateGameSettings(safeCanGuessWord)
                        }
                    }

                    is GameSocketEvents.GameStartedEvent -> {
                        event.gameInfo?.let { safeGameInfo ->
                            gameStarted(safeGameInfo)
                        }
                    }

                    is GameSocketEvents.GameUpdateEvent -> {
                        event.gameUpdate?.let { safeGameUpdate ->
                            gameUpdate(safeGameUpdate)
                        }
                    }

                    is GameSocketEvents.PlayerEliminatedEvent -> {
                        event.userId?.let { safeEliminatedUserId ->
                            playerEliminated(safeEliminatedUserId)
                        }
                    }

                    is GameSocketEvents.PlayerJoinedEvent -> {
                        event.playerJoined?.let { safePlayerJoined ->
                            playerJoined(safePlayerJoined)
                        }
                    }

                    is GameSocketEvents.PlayerLeftEvent -> {
                        event.userId?.let { safeLeftUserId ->
                            playerLeft(safeLeftUserId)
                        }
                    }

                    is GameSocketEvents.PlayerReadyUpdatedEvent -> {
                        event.playerReadyUpdated?.let { safePlayerReadyUpdated ->
                            playerReadyUpdated(safePlayerReadyUpdated)
                        }
                    }

                    is GameSocketEvents.TurnEvent -> {
                        event.turn?.let { safeTurn ->
                            turn(safeTurn)
                        }
                    }

                    is GameSocketEvents.RoomStateEvent -> {
                        event.roomState?.let {safeRoomState ->
                            updateRoomState(safeRoomState)
                        }
                    }
                }
            }
        }
    }

    private fun updateRoomState(roomState : RoomState) {
        _state.update {
            it.copy(
                players = roomState.players
            )
        }
    }

    private fun playerEliminated(userId: String) {
        val myUserId = _state.value.userId ?: ""
        if (myUserId == userId) {
            _state.update {
                it.copy(
                    isGameFinished = true,
                    gameResult = GameResult.YOU_ELIMINATED,
                    word = null
                )
            }
        } else {
            _state.update {
                it.copy(
                    players = it.players.map { player ->
                        if (player.id == userId) {
                            player.copy(eliminated = true)
                        } else {
                            player
                        }
                    }
                )
            }
        }

    }

    private fun gameOver(gameOver: GameOver) {
        val myUsername = _state.value.username ?: ""
        _state.update {
            it.copy(
                isGameFinished = true,
                word = gameOver.word,
                winner = gameOver.winner,
                gameResult = when (gameOver.winner) {
                    myUsername -> {
                        GameResult.YOU_WINNER
                    }

                    null -> {
                        GameResult.DRAW
                    }

                    else -> {
                        GameResult.OPPONENT_WINNER
                    }
                }
            )
        }
    }


    private fun updateGameSettings(canGuessWord: Boolean) {
        _state.update {
            it.copy(
                canGuessWord = canGuessWord
            )
        }
    }

    private fun gameUpdate(gameUpdate: GameUpdate) {
        updateDiscoveredLetters(gameUpdate)
        updateKeyColors(gameUpdate)
        updateWrongGuessesCount(gameUpdate)
        updatePlayerGuessedKey(gameUpdate)
    }

    private fun updatePlayerGuessedKey(gameUpdate: GameUpdate) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    playerGuessedKey = Triple(
                        gameUpdate.guessedBy,
                        gameUpdate.guessedLetter,
                        gameUpdate.correct
                    )
                )
            }
            delay(2000)
            _state.update {
                it.copy(
                    playerGuessedKey = null
                )
            }
        }
    }

    private fun updateWrongGuessesCount(gameUpdate: GameUpdate) {
        gameUpdate.wrongGuesses?.let { safeWrongGuessesCount ->
            _state.update {
                it.copy(
                    wrongGuessesCount = safeWrongGuessesCount
                )
            }
        }
    }

    private fun updateDiscoveredLetters(gameUpdate: GameUpdate) {
        gameUpdate.discovered?.let { safeDiscoveredLetters ->
            _state.update {
                it.copy(
                    discoveredLetters = safeDiscoveredLetters
                )
            }
        }
    }

    private fun updateKeyColors(gameUpdate: GameUpdate) {
        _state.update {
            it.copy(
                keyColors = _state.value.keyColors + listOf(
                    Pair(
                        gameUpdate.guessedLetter,
                        if (gameUpdate.correct) Color(0xFF305B31) else Red
                    )
                )
            )
        }
    }


    private fun playerReadyUpdated(update: PlayerReadyUpdated) {
        _state.update { state ->
            state.copy(
                players = state.players.map { player ->
                    if (player.id == update.userId) {
                        player.copy(ready = update.ready)
                    } else {
                        player
                    }
                }
            )
        }
    }


    private fun turn(turn: Turn) {
        timerJob?.cancel()
        _state.update {
            it.copy(
                currentProgress = 0f,
                secondsLeft = 30
            )
        }
        val totalMs = 30_000L
        val step = 1000L
        val steps = totalMs / step
        timerJob = viewModelScope.launch {
            repeat(steps.toInt()) { i ->
                _state.update {
                    it.copy(
                        currentProgress = (i + 1) / steps.toFloat(),
                        secondsLeft = (steps - (i + 1)).toInt()
                    )
                }
                delay(step)
            }

        }

        val myUsername = _state.value.username ?: ""
        _state.update {
            it.copy(
                turnPlayer = turn.name,
                isYourTurn = turn.name == myUsername
            )
        }
    }

    private fun playerLeft(userId: String) {
        _state.update {
            it.copy(
                players = it.players - it.players.first { player -> player.id == userId }
            )
        }
    }

    private fun playerJoined(safePlayerJoined: PlayerJoined) {
        _state.update {
            it.copy(
                players = it.players + safePlayerJoined.toPlayer()
            )
        }
    }

    private fun gameStarted(gameInfo: GameInfo) {
        _state.update {
            it.copy(
                isGameStarted = true,
                wordLength = gameInfo.wordLength,
                players = gameInfo.players
            )
        }
    }
}
