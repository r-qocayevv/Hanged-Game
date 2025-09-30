package com.revan.hanged.presentation.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.revan.hanged.R
import com.revan.hanged.domain.GameLanguage
import com.revan.hanged.domain.GameResult
import com.revan.hanged.domain.model.RoomInfo
import com.revan.hanged.presentation.game.components.ConfirmExitBottomSheet
import com.revan.hanged.presentation.game.components.GameBody
import com.revan.hanged.presentation.game.components.GameFooter
import com.revan.hanged.presentation.game.components.GameHeader
import com.revan.hanged.presentation.game.components.GameResultBottomSheet
import com.revan.hanged.presentation.game.components.PlayerModalBottomSheet

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    roomInfo : RoomInfo,
    uiState : GameState,
    onEvent : (GameEvent) -> Unit
) {

    val headerText = when (uiState.gameResult) {
        GameResult.DRAW -> {
            stringResource(R.string.draw)
        }
        GameResult.YOU_WINNER -> {
            stringResource(R.string.congratulations)
        }
        GameResult.OPPONENT_WINNER -> {
            stringResource(R.string.round_over)
        }
        GameResult.YOU_ELIMINATED -> {
            stringResource(R.string.you_are_eliminated)
        }
    }

    val contentText = when (uiState.gameResult) {
        GameResult.DRAW -> {
            stringResource(R.string.draw_content)
        }
        GameResult.YOU_WINNER -> {
            stringResource(R.string.you_winner_content)
        }
        GameResult.OPPONENT_WINNER -> {
            stringResource(R.string.opponent_winner_content, uiState.winner ?: "")
        }
        GameResult.YOU_ELIMINATED -> {
            stringResource(R.string.eliminated_content)
        }
    }

    LazyColumn (
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFF1F262D))
            .navigationBarsPadding(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        item {
            GameHeader(roomInfo = roomInfo,uiState = uiState, onEvent = onEvent)
            GameBody(uiState = uiState)
        }
        item {
            GameFooter(roomInfo = roomInfo,uiState = uiState,onEvent = onEvent)

            if (uiState.isConfirmExitBottomSheetOpen) {
                ConfirmExitBottomSheet(
                    onDismiss = {
                        onEvent(GameEvent.ChangeConfirmExitBottomSheetState)
                    }, exitGame = {
                        onEvent(GameEvent.LeaveRoom(roomInfo = roomInfo))
                    }
                )
            }

            if (uiState.isGameFinished) {
                GameResultBottomSheet(headerText = headerText, contentText = contentText, onClick = {
                    onEvent(GameEvent.LeaveRoom(roomInfo = roomInfo))
                },
                    word = uiState.word ?: "")
            }

            if (uiState.isPlayerBottomSheetOpen) {
                PlayerModalBottomSheet(maxPlayerCount = roomInfo.maxPlayerCount, uiState = uiState, onDismiss = {
                    onEvent(GameEvent.ChangePlayerBottomSheetState)
                })
            }
        }

    }
}

@Preview
@Composable
private fun GameScreenPrev() {
    GameScreen(
        roomInfo = RoomInfo(
            roomId = "123",
            userId = "123",
            userName = "Ravan",
            difficulty = "Easy",
            gameLanguage = GameLanguage.AZE
        ),
        uiState = GameState(),
        onEvent = {}
    )
}