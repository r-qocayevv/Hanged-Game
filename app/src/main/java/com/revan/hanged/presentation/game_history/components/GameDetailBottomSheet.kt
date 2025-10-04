package com.revan.hanged.presentation.game_history.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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
import com.revan.hanged.domain.model.Game
import com.revan.hanged.domain.model.Player
import com.revan.hanged.domain.model.Winner
import com.revan.hanged.presentation.game_history.GameHistoryState
import com.revan.hanged.ui.theme.LightGray
import com.revan.hanged.ui.theme.Red
import com.revan.hanged.utils.firstCharToUpperCase
import com.revan.hanged.utils.formatIsoToCustom

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDetailBottomSheet(
    modifier: Modifier = Modifier,
    uiState: GameHistoryState,
    onDismiss: () -> Unit
) {

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    ModalBottomSheet(
        containerColor = Color(0xFF2A303C),
        modifier = modifier
            .navigationBarsPadding()
            .padding(bottom = 8.dp)
            .padding(horizontal = 8.dp),
        sheetState = sheetState,
        shape = RoundedCornerShape(16.dp),
        onDismissRequest = {
            onDismiss()
        },
        content = {
            GameDetailBottomSheetContent(
                myId = uiState.myId,
                game = uiState.selectedGame,
                activePlayers = uiState.activePlayer,
                eliminatedPlayers = uiState.eliminatedPlayers
            )
        }
    )
}

@Composable
fun GameDetailBottomSheetContent(
    modifier: Modifier = Modifier,
    activePlayers: List<Player>,
    eliminatedPlayers: List<Player>,
    myId: String,
    game: Game
) {

    val areYouWinner = game.winner?.id == myId

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 29.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(

            ) {
                Text(
                    text = game.roomName,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    lineHeight = 20.sp,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,

                    )
                Text(
                    text = game.createdAt.formatIsoToCustom(),
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    lineHeight = 12.sp,
                    color = LightGray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            Box(
                modifier = Modifier.background(
                    color = if (areYouWinner) Color(0x333EB481) else Color(
                        0x33C40100
                    ), shape = CircleShape
                )
            ) {
                Text(
                    text = if (areYouWinner) stringResource(R.string.won) else stringResource(R.string.lost),
                    fontSize = 10.sp,
                    color = if (areYouWinner) Color(0xFF3EB481) else Color(0xFFC40100),
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 18.sp,
                    modifier = Modifier.padding(horizontal = 9.dp)
                )

            }
        }
        Spacer(Modifier.height(14.dp))
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0x0DACAEB1), shape = RoundedCornerShape(15.dp))
                .padding(vertical = 14.dp, horizontal = 36.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.Top
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            //todo *
                            Text(
                                text = game.word.uppercase(),
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 24.sp,
                                lineHeight = 24.sp,
                                color = Color.White,
                            )
                            //todo *
                            Text(
                                text = "${game.language.firstCharToUpperCase()} | ${game.difficulty.firstCharToUpperCase()}",
                                fontWeight = FontWeight.Medium,
                                lineHeight = 16.sp,
                                fontSize = 7.sp,
                                color = LightGray,
                            )
                        }
                        Spacer(Modifier.width(8.dp))
                        //todo
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_show_password),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier.padding(top = 6.dp)
                        )
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    repeat(3) {
                        val label = when (it) {
                            0 -> "Your score"
                            1 -> "Players"
                            2 -> "Wrong"
                            else -> ""
                        }

                        val labelValue = when (it) {
                            0 -> (game.players.find { it.id == myId }?.score ?: 0).toString()
                            1 -> game.players.size.toString()

                            2 -> "${game.wrongGuesses}/10"
                            else -> ""
                        }

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = label,
                                fontWeight = FontWeight.Medium,
                                fontSize = 10.sp,
                                lineHeight = 10.sp,
                                color = LightGray,
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(
                                text = labelValue,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp,
                                lineHeight = 16.sp,
                                color = Color.White,
                            )
                        }
                    }
                }
            }
        }
        Spacer(Modifier.height(12.dp))
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .background(color = Color(0x1AD4D413), shape = RoundedCornerShape(16.dp))
                .border(width = 1.dp, color = Color(0x80D4D413), shape = RoundedCornerShape(16.dp))
        ) {
            game.winner?.let {
                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.padding(horizontal = 9.dp, vertical = 8.dp)
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(R.string.winner),
                            fontWeight = FontWeight.Medium,
                            fontSize = 10.sp,
                            lineHeight = 10.sp,
                            color = LightGray,
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = it.name,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            lineHeight = 16.sp,
                            color = Color.White,
                        )
                    }

                    Text(
                        text = "${it.score} points",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 10.sp,
                        lineHeight = 10.sp,
                        color = Color(0xFFD4D413),
                    )
                }

            }
        }
        Spacer(Modifier.height(18.dp))

        if (activePlayers.isNotEmpty()) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(5.dp)
                            .background(color = Color(0xFF3EB481), shape = CircleShape)
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = "Active players (${activePlayers.size})",
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                    )

                }
                Spacer(Modifier.height(12.dp))
                activePlayers.forEach {
                    GameDetailPlayersCustomBox(player = it)
                    Spacer(Modifier.height(8.dp))
                }
            }
        }

        if (eliminatedPlayers.isNotEmpty()) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(5.dp)
                            .background(color = Red, shape = CircleShape)
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = "Eliminated players (${eliminatedPlayers.size})",
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                    )
                }
                Spacer(Modifier.height(12.dp))
                eliminatedPlayers.forEach {
                    GameDetailPlayersCustomBox(player = it)
                    Spacer(Modifier.height(8.dp))
                }
            }
        }


    }
}

@Preview
@Composable
private fun GameDetailBottomSheetPrev() {
    GameDetailBottomSheetContent(
        game = Game(
            createdAt = "2025-10-01T09:36:58.000Z",
            difficulty = "Easy",
            language = "az",
            roomName = "Room Name",
            roomId = "123456789",
            winner = Winner("123", "Raven", 0),
            word = "word",
            players = emptyList(),
            wrongGuesses = 0
        ), myId = "123", eliminatedPlayers = emptyList(), activePlayers = emptyList()
    )
}
