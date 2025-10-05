package com.revan.hanged.presentation.game_history.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revan.hanged.R
import com.revan.hanged.domain.model.Player
import com.revan.hanged.ui.theme.LightGray

@Composable
fun GameDetailPlayersCustomBox(
    modifier: Modifier = Modifier,
    isWinner: Boolean = false,
    player: Player
) {

    val color = if (player.eliminated) Color(0xFFC40100) else Color(0xFF3EB481)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = color.copy(alpha = 0.1f), shape = RoundedCornerShape(16.dp)
            )
            .border(
                width = 1.dp,
                color = color.copy(alpha = 0.5f),
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 10.dp)
                .padding(end = 4.dp)
        ) {
            Box(
                Modifier
                    .background(color = color.copy(0.5f), shape = CircleShape).size(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = player.name.first().uppercase(),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    color = Color.White,
                )
            }
            Spacer(Modifier.width(9.dp))
            Column (
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = player.name,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                    color = Color.White,
                )
                if (isWinner) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_crown),
                            contentDescription = null,
                            tint = Color.Unspecified

                        )
                        Spacer(Modifier.width(2.dp))
                        Text(
                            text = stringResource(R.string.winner),
                            fontWeight = FontWeight.Medium,
                            fontSize = 8.sp,
                            lineHeight = 8.sp,
                            color = Color(0xFFD4D413),
                        )
                    }
                } else if (player.eliminated) {
                    Text(
                        text = stringResource(R.string.eliminated),
                        fontWeight = FontWeight.Medium,
                        fontSize = 8.sp,
                        lineHeight = 8.sp,
                        color = color,
                    )
                }
            }
            Spacer(Modifier.width(8.dp))
            Column (
                horizontalAlignment = Alignment.End
            ){
                Text(
                    text = player.score.toString(),
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    lineHeight = 12.sp,
                    color = Color.White,
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text = stringResource(R.string.points),
                    fontSize = 8.sp,
                    lineHeight = 8.sp,
                    color = LightGray,
                )
            }
        }
    }
}

@Preview
@Composable
private fun GameDetailPlayersCustomBoxPrev() {
    GameDetailPlayersCustomBox(
        player = Player(
            eliminated = false,
            id = "123",
            name = "Raven",
            score = 0,
        ),
        isWinner = true
    )
}