package com.revan.hanged.presentation.game_history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revan.hanged.R
import com.revan.hanged.presentation.components.Tabs
import com.revan.hanged.presentation.game_history.components.GameDetailBottomSheet
import com.revan.hanged.presentation.game_history.components.GameHistoryListItem
import com.revan.hanged.presentation.game_history.components.MyOwnGameHistoryInfo
import com.revan.hanged.presentation.game_history.components.RankInLeaderboard
import com.revan.hanged.presentation.game_history.components.YourRaking
import com.revan.hanged.ui.theme.DarkGray
import com.revan.hanged.ui.theme.LightGray
import com.revan.hanged.ui.theme.Red
import com.revan.hanged.utils.clickWithoutRipple

@Composable
fun GameHistoryScreen(
    modifier: Modifier = Modifier,
    uiState: GameHistoryState,
    onEvent: (GameHistoryEvent) -> Unit
) {

    val tabs = listOf("History", "Leaderboard")

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = DarkGray)
            .systemBarsPadding()
            .then(other = Modifier.blur(if (uiState.isGameDetailBottomSheetOpen) 8.dp else 0.dp))
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .padding(top = 10.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_back),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(24.dp)
                    .clickWithoutRipple(
                        onClick = {
                            onEvent(GameHistoryEvent.ToBack)
                        }
                    )
            )
        }

        if (uiState.isGameDetailBottomSheetOpen) {
            GameDetailBottomSheet(
                uiState = uiState,
                onDismiss = {
                    onEvent(GameHistoryEvent.CloseGameDetailBottomSheet)
                },
                changeWordVisibility = {
                    onEvent(GameHistoryEvent.ChangeWordVisibility)
                }
            )
        }

        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .background(color = DarkGray)
        ) {

            if (uiState.isLoading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillParentMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = Red, strokeWidth = 5.dp)
                    }
                }
            }

            item {
                MyOwnGameHistoryInfo(uiState = uiState)
                Spacer(Modifier
                    .height(4.dp)
                    .background(DarkGray))
            }
            stickyHeader {
                Tabs(
                    tabs = tabs,
                    selectedTabIndex = uiState.selectedTabIndex,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    onItemSelection = {
                        onEvent(GameHistoryEvent.OnTabSelected(it))
                    })
            }


            when (uiState.selectedTabIndex) {
                0 -> {
                    items(uiState.games) { game ->
                        GameHistoryListItem(
                            game = game,
                            myId = uiState.myId,
                            showGameDetailDialog = {
                                onEvent(GameHistoryEvent.OnGameSelected(game = game))
                            })
                        Spacer(Modifier.height(8.dp))
                    }
                }


                1 -> {
                    item {
                        YourRaking(myPlaceInLeaderboard = uiState.myRank, uiState = uiState)
                    }


                    items(uiState.leaderboard) {
                        val isMyRank = it.userId == uiState.myId
                        Spacer(Modifier.height(6.dp))
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .background(
                                    color = if (isMyRank) Color(0xFF353C4B) else Color(0x66353C4B),
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .padding(vertical = 12.dp, horizontal = 16.dp)
                        ) {
                            RankInLeaderboard(
                                leaderboard = it,
                                isMyRank = isMyRank,
                                uiState = uiState
                            )
                        }
                    }
                }
            }
            if ((uiState.selectedTabIndex == 0 && uiState.games.isEmpty()) || (uiState.selectedTabIndex == 1 && uiState.leaderboard.isEmpty())) {
                item {
                    Column(
                        modifier = Modifier
                            .fillParentMaxHeight(0.5f)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(if (uiState.selectedTabIndex == 0) R.drawable.ic_history else R.drawable.ic_empty_leaderboard),
                            contentDescription = null,
                            tint = LightGray
                        )
                        Spacer(Modifier.height(16.dp))
                        Text(
                            text = stringResource(R.string.there_is_nothing_here),
                            color = LightGray,
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp,
                        )
                    }
                }
            }
        }
    }

}

@Preview
@Composable
private fun GameHistoryScreenPrev() {
    GameHistoryScreen(onEvent = {}, uiState = GameHistoryState())
}