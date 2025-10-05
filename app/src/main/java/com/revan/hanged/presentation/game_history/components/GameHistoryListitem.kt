package com.revan.hanged.presentation.game_history.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revan.hanged.R
import com.revan.hanged.domain.model.Game
import com.revan.hanged.ui.theme.DarkGray
import com.revan.hanged.ui.theme.LightGray
import com.revan.hanged.ui.theme.Red
import com.revan.hanged.utils.clickWithoutRipple
import com.revan.hanged.utils.firstCharToUpperCase
import com.revan.hanged.utils.getTimeAgo

@Composable
fun GameHistoryListItem(
    modifier: Modifier = Modifier,
    game: Game,
    myId: String,
    showGameDetailDialog : () -> Unit
) {
    val areYouWinner = game.winner != null && game.winner.id == myId

    Box(
        modifier = modifier
            .height(IntrinsicSize.Max)
            .fillMaxWidth()
            .padding(end = 16.dp, start = 16.dp)
            .background(color = Color(0xFF2A303C), shape = RoundedCornerShape(10.dp))
            .clip(shape = RoundedCornerShape(10.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(10.dp)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            VerticalDivider(
                modifier = Modifier.fillMaxHeight(),
                thickness = 11.dp,
                color = if (areYouWinner) Color(0xFF3EB481) else Red
            )

            Icon(
                imageVector = ImageVector.vectorResource(if (areYouWinner) R.drawable.ic_winner_cup else R.drawable.ic_lose),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(18.dp),
            modifier = Modifier
                .padding(top = 11.dp, bottom = 8.dp)
                .padding(start = 24.dp, end = 8.dp)
        ) {

            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {

                    Text(
                        text = game.word.firstCharToUpperCase(),
                        fontSize = 24.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )

                    Text(
                        text = game.roomName,
                        fontSize = 10.sp,
                        lineHeight = 10.sp,
                        color = LightGray
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(11.dp)
                ) {
                    Text(
                        text = "+${if (areYouWinner) game.winner.score else 0}",
                        fontSize = 16.sp,
                        lineHeight = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = LightGray
                    )
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_right),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.clickWithoutRipple(onClick = {
                            showGameDetailDialog()
                        })
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.Bottom,
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_player),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                        Text(
                            text = game.players.size.toString(),
                            fontSize = 12.sp,
                            lineHeight = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = LightGray

                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_flame),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )

                        Text(
                            text = game.difficulty.firstCharToUpperCase(),
                            fontSize = 12.sp,
                            lineHeight = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = LightGray
                        )
                    }
                }
                Spacer(Modifier.width(16.dp))
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_language),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                        Text(
                            text = game.roomName.firstCharToUpperCase(),
                            fontSize = 12.sp,
                            lineHeight = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = LightGray

                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_calendar),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                        Text(
                            text = game.createdAt.getTimeAgo(),
                            fontSize = 12.sp,
                            lineHeight = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = LightGray
                        )
                    }
                }

                val users = game.players

                val visibleUsers = users.take(2)
                val remaining = users.size - 2

                Row(
                    horizontalArrangement = Arrangement.End,
                ) {
                    visibleUsers.forEachIndexed { index, initial ->
                        Box(
                            modifier = Modifier
                                .offset(x = (-8 * index).dp)
                                .size(28.dp)
                                .border(
                                    width = 3.dp,
                                    color = Color(0xFF2A303C),
                                    shape = CircleShape
                                )
                                .background(Color(0xFFD9D9D9), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = initial.name.take(1).uppercase(),
                                color = DarkGray,
                                fontSize = 10.sp,
                                lineHeight = 10.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    if (remaining > 0) {
                        Box(
                            modifier = Modifier
                                .offset(x = (-8 * visibleUsers.size).dp)
                                .size(28.dp)
                                .border(
                                    width = 3.dp,
                                    color = Color(0xFF2A303C),
                                    shape = CircleShape
                                )
                                .background(Color(0xFFD9D9D9), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "+$remaining",
                                color = DarkGray,
                                fontSize = 10.sp,
                                lineHeight = 10.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }

            }
        }
    }
}


@Preview
@Composable
private fun GameHistoryListItemPrev() {
    GameHistoryListItem(
        game =
            Game(
                createdAt = "2025-09-30T05:22:19.623Z",
                difficulty = "",
                language = "",
                players = emptyList(),
                roomId = "",
                roomName = "",
                winner = null,
                word = "Omar",
                wrongGuesses = 0
            ),
        myId = "",
        showGameDetailDialog = {}
    )
}