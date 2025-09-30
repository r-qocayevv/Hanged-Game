package com.revan.hanged.presentation.game.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.revan.hanged.R
import com.revan.hanged.domain.GameLanguage
import com.revan.hanged.domain.model.RoomInfo
import com.revan.hanged.presentation.components.CustomButton
import com.revan.hanged.presentation.game.GameEvent
import com.revan.hanged.presentation.game.GameState
import com.revan.hanged.ui.theme.Red
import com.revan.hanged.utils.clickWithoutRipple

@Composable
fun GameFooter(
    modifier: Modifier = Modifier,
    roomInfo: RoomInfo,
    uiState: GameState,
    onEvent: (GameEvent) -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            if (!uiState.isGameStarted) {
                CustomButton(
                    modifier = Modifier
                        .weight(1f)
                        .alpha(if (uiState.isReady) 0.5f else 1f),
                    textVerticalPadding = 12.dp,
                    text = if (uiState.isReady) stringResource(R.string.cancel) else stringResource(
                        R.string.ready
                    ),
                    isButtonEnabled = true,
                    onClick = {
                        onEvent(GameEvent.ChangeReadyState(roomInfo = roomInfo))
                    })
            } else {
                AnimatedContent(
                    modifier = Modifier.weight(1f),
                    targetState = uiState.isGuessWordOpen
                ) { isGuessWordOpen ->

                    if (isGuessWordOpen) {
                        GuestWordTextField(
                            modifier = Modifier.weight(1f),
                            uiState = uiState,
                            onEvent = onEvent,
                            roomInfo = roomInfo
                        )
                    }
                }
            }
            Spacer(Modifier.width(10.dp))
            AnimatedContent(targetState = uiState.isGuessWordOpen) {isGuessWordOpen ->
                Box(
                    modifier = Modifier
                        .background(color = Red.copy(alpha = 0.5f), shape = CircleShape)
                        .border(width = 1.dp, color = Red, shape = CircleShape)
                        .padding(
                            horizontal = 17.dp, vertical = 14.dp
                        )
                        .alpha(if (uiState.canGuessWord) 1f else 0.5f),

                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(if (uiState.isGuessWordOpen) R.drawable.ic_close else R.drawable.ic_quest_word),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.clickWithoutRipple(
                            isEnabled = uiState.canGuessWord,
                            onClick = {
                                onEvent(GameEvent.OnOpenGuessWord)
                            }
                        )
                    )
                }
            }
        }
        Spacer(Modifier.height(14.dp))
        Keyboard(
            layout = roomInfo.gameLanguage,
            keyColors = uiState.keyColors,
            isYourTurn = uiState.isYourTurn,
            isCanGuessWordOpen = uiState.isGuessWordOpen,
            onKeyClick = {
                onEvent(GameEvent.KeyClicked(key = it, roomInfo = roomInfo))
            })
    }
}

@Preview
@Composable
private fun GameFooterPrev() {
    GameFooter(
        uiState = GameState(isGameStarted = true, canGuessWord = true, isGuessWordOpen = true),
        onEvent = {},
        roomInfo = RoomInfo(
            roomId = "123",
            userId = "123",
            userName = "Ravan",
            difficulty = "Easy",
            gameLanguage = GameLanguage.AZE
        )
    )
}