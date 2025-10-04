package com.revan.hanged.presentation.game.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revan.hanged.R
import com.revan.hanged.domain.model.RoomInfo
import com.revan.hanged.presentation.game.GameEvent
import com.revan.hanged.presentation.game.GameState
import com.revan.hanged.ui.theme.DarkGray
import com.revan.hanged.ui.theme.LightGray
import com.revan.hanged.utils.clickWithoutRipple
import com.revan.hanged.utils.firstCharToUpperCase

@Composable
fun GameHeader(
    modifier: Modifier = Modifier,
    roomInfo: RoomInfo,
    uiState: GameState,
    onEvent: (GameEvent) -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val animatedProgress by animateFloatAsState(
            targetValue = uiState.currentProgress,
            animationSpec = tween(durationMillis = 1000),
        )

        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(color = DarkGray)
                .padding(start = 16.dp, end = 8.dp, top = 16.dp, bottom = 16.dp)
                .statusBarsPadding(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_back),
                contentDescription = null,
                tint = LightGray,
                modifier = Modifier.clickWithoutRipple(onClick = {
                    if (uiState.isGameStarted) {
                        onEvent(GameEvent.ChangeConfirmExitBottomSheetState)
                    } else {
                        onEvent(GameEvent.LeaveRoom(roomInfo = roomInfo))
                    }
                })
            )
            Spacer(Modifier.width(10.dp))
            Text(
                text = stringResource(R.string.leave),
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = LightGray,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_flame),
                contentDescription = null,
                tint = LightGray
            )
            Spacer(Modifier.width(4.dp))

            Text(
                text = roomInfo.difficulty.firstCharToUpperCase(),
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = LightGray,
                modifier = Modifier.clickWithoutRipple(onClick = {
                    onEvent(GameEvent.ChangePlayerBottomSheetState)
                })
            )
            Spacer(Modifier.width(8.dp))
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_language),
                contentDescription = null,
                tint = LightGray
            )
            Spacer(Modifier.width(4.dp))

            Text(
                text = roomInfo.gameLanguage.language.firstCharToUpperCase(),
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = LightGray,
                modifier = Modifier.clickWithoutRipple(onClick = {
                    onEvent(GameEvent.ChangePlayerBottomSheetState)
                })
            )
            Spacer(Modifier.width(8.dp))
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_player),
                contentDescription = null,
                tint = LightGray
            )
            Spacer(Modifier.width(4.dp))

            Text(
                text = "${uiState.players.size}/${roomInfo.maxPlayerCount}",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = LightGray,
                modifier = Modifier.clickWithoutRipple(onClick = {
                    onEvent(GameEvent.ChangePlayerBottomSheetState)
                })
            )
            Spacer(Modifier.width(8.dp))
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_waiting),
                contentDescription = null,
                tint = LightGray
            )
            Spacer(Modifier.width(4.dp))
            Text(
                text = uiState.secondsLeft.toString(),
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = LightGray,
            )

            Spacer(Modifier.height(12.dp))

        }
        Spacer(Modifier.height(15.dp))
        TurnInfo(uiState = uiState)
        Spacer(Modifier.height(8.dp))
        LinearProgressIndicator(
            progress = { animatedProgress },
            gapSize = 1.dp,
            modifier = Modifier.padding(horizontal = 70.dp),
            color = Color(0xFF949699),
            drawStopIndicator = {},
            trackColor = Color(0xFFD9D9D9),
            strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
        )
    }


}

@Preview
@Composable
private fun GameHeaderPrev() {
    GameHeader(
        uiState = GameState(), roomInfo = RoomInfo(
            roomId = "123",
            userId = "123",
            userName = "123",
            difficulty = "easy",
            gameLanguage = com.revan.hanged.domain.GameLanguage.ENG,
            maxPlayerCount = 4,
            currentPlayerCount = 2
        ),
        onEvent = {})
}