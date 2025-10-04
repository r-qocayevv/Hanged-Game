package com.revan.hanged.presentation.game_history.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revan.hanged.R
import com.revan.hanged.domain.model.Leaderboard
import com.revan.hanged.presentation.game_history.GameHistoryState
import com.revan.hanged.ui.theme.LightGray

@Composable
fun RankInLeaderboard(
    modifier: Modifier = Modifier,
    leaderboard: Leaderboard?,
    uiState : GameHistoryState,
    isMyRank: Boolean = false
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(
            text = (leaderboard?.rank ?: "-" ).toString(),
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp
        )
        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color(0xFF383D45)),
            modifier = Modifier
                .clip(CircleShape)
                .border(width = 1.dp, color = Color.White, shape = CircleShape)
                .size(36.dp),

            )
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = if (isMyRank) "You" else leaderboard?.username ?: uiState.myUsername,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
            Text(
                text = leaderboard?.userId ?: uiState.myId,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = LightGray
            )
        }

        Text(
            text = (leaderboard?.totalScore ?: 0).toString(),
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White
        )
    }
}

@Preview
@Composable
private fun RankInLeaderboardPrev() {
    RankInLeaderboard(
        leaderboard = null,
        uiState = GameHistoryState()
    )
}