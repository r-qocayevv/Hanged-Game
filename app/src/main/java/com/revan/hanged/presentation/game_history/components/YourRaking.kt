package com.revan.hanged.presentation.game_history.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revan.hanged.R
import com.revan.hanged.domain.model.Leaderboard
import com.revan.hanged.presentation.game_history.GameHistoryState

@Composable
fun YourRaking(
    modifier: Modifier = Modifier,
    uiState : GameHistoryState,
    myPlaceInLeaderboard: Leaderboard?,
) {
    Box(
        modifier = modifier.height(IntrinsicSize.Max).padding(horizontal = 16.dp).background(
            color = Color(0xFF353C4B),
            shape = RoundedCornerShape(10.dp)
        )
    ) {
        Image(
            painter = painterResource(R.drawable.ic_your_ranking_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().fillMaxHeight()
        )

        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(R.string.your_ranking),
                color = Color.White,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp

            )

            RankInLeaderboard(leaderboard = myPlaceInLeaderboard, uiState = uiState)

        }
    }
}

@Preview
@Composable
private fun YourRakingPrev() {
    YourRaking(
        myPlaceInLeaderboard = Leaderboard(
            rank = 1,
            totalScore = 100,
            userId = "",
            username = "Omar"
        ),
        uiState = GameHistoryState()
    )
}