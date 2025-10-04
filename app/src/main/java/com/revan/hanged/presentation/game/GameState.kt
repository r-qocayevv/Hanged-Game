package com.revan.hanged.presentation.game

import androidx.compose.ui.graphics.Color
import com.revan.hanged.domain.GameResult
import com.revan.hanged.domain.model.Player

data class GameState(
    val wordLength : Int = 0,
    val canGuessWord : Boolean = false,
    val username : String? = null,
    val userId : String? = null,
    val isGuessWordOpen : Boolean = false,
    val guessWord : String = "",
    val discoveredLetters : List<String> = emptyList(),
    val isReady : Boolean = false,
    val isGameStarted : Boolean = false,
    val isGameFinished : Boolean = false,
    val isYourTurn : Boolean = false,
    val turnPlayer : String = "",
    val players : List<Player> = emptyList(),
    val winner : String? = "",
    val word : String? = null,
    var currentProgress : Float = 0f,
    var secondsLeft : Int = 0,
    val wrongGuessesCount : Int = 0,
    val keyColors : List<Pair<String,Color>> = emptyList(),
    val gameResult: GameResult = GameResult.OPPONENT_WINNER,
    val isPlayerBottomSheetOpen : Boolean = false,
    val isConfirmExitBottomSheetOpen : Boolean = false,
    val playerGuessedKey : Triple<String, String, Boolean>? = null
)
