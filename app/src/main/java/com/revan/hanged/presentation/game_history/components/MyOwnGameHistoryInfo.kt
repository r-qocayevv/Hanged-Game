package com.revan.hanged.presentation.game_history.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revan.hanged.R
import com.revan.hanged.presentation.game_history.GameHistoryState

@Composable
fun MyOwnGameHistoryInfo(
    modifier: Modifier = Modifier,
    uiState: GameHistoryState
) {

    val myOwnDetails = uiState.myOwnDetails

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 10.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFFC40100),
                        Color(0xFFA00100)
                    )
                ),
                shape = RoundedCornerShape(10.dp)
            )

    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.CenterEnd
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_cup),
                contentDescription = null,
                tint = Color.Unspecified,
            )
        }

        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 22.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Column(

            ) {
                Text(
                    text = stringResource(R.string.total_score),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
                Spacer(Modifier.height(5.dp))
                Text(
                    text = (myOwnDetails?.totalScore ?: 0).toString(),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(17.dp)
            ) {
                repeat(times = 3) {
                    Column(
                        modifier = Modifier
                            .background(
                                color = Color(0x33ACAEB1),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(
                                vertical = 5.dp,
                                horizontal = 8.dp
                            )
                            .weight(1f),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        val label = when (it) {
                            0 -> "Games"
                            1 -> "Wins"
                            2 -> "Losses"
                            else -> ""
                        }

                        val count = when (it) {
                            0 -> myOwnDetails?.totalGames ?: 0
                            1 -> myOwnDetails?.wins ?: 0
                            2 -> myOwnDetails?.losses ?: 0
                            else -> 0
                        }

                        Text(
                            text = label,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White
                        )
                        Text(
                            text = count.toString(),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun MyOwnGameHistoryInfoPrev() {
    MyOwnGameHistoryInfo(uiState = GameHistoryState())
}