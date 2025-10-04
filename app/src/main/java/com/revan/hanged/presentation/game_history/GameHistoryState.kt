package com.revan.hanged.presentation.game_history

import com.revan.hanged.domain.model.Game
import com.revan.hanged.domain.model.Leaderboard
import com.revan.hanged.domain.model.Player
import com.revan.hanged.domain.model.User

data class GameHistoryState(
    val isLoading: Boolean = false,
    val myId: String = "",
    val myUsername : String = "",
    val games: List<Game> = emptyList(),
    val selectedTabIndex: Int = 0,
    val leaderboard: List<Leaderboard> = emptyList(),
    val isGameDetailBottomSheetOpen : Boolean = false,
    val eliminatedPlayers : List<Player> = emptyList(),
    val activePlayer: List<Player> = emptyList(),
    val myOwnDetails: User? = null,
    val myRank : Leaderboard? = null,
    val selectedGame : Game = Game (
        createdAt = "",
        difficulty = "",
        language = "",
        players = emptyList(),
        roomId = "",
        roomName = "",
        winner = null,
        word = "",
        wrongGuesses = 0
    ),
)