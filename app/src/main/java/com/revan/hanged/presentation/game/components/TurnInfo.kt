package com.revan.hanged.presentation.game.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revan.hanged.R
import com.revan.hanged.presentation.game.GameState
import com.revan.hanged.ui.theme.LightGray
import com.revan.hanged.ui.theme.Red

@Composable
fun TurnInfo(
    modifier: Modifier = Modifier,
    uiState: GameState
) {

    Box(
        modifier = modifier.background(
            color =
                if (uiState.isGameStarted) {
                    if (uiState.isYourTurn) {
                        Red
                    } else {
                        LightGray
                    }
                } else {
                    LightGray
                },
            shape = RoundedCornerShape(6.dp)
        ),
        contentAlignment = Alignment.Center
    ) {
        Text(

            text =
                if (uiState.isGameStarted) {
                    if (uiState.isYourTurn) {
                        stringResource(R.string.your_turn)
                    } else {
                        stringResource(R.string.s_turn, uiState.turnPlayer)
                    }
                } else {
                    stringResource(R.string.players_are_not_ready)
                },
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            fontSize = 12.sp,
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 6.dp)
        )
    }
}

@Preview
@Composable
private fun TurnInfoPrev() {
    TurnInfo(uiState = GameState())
}