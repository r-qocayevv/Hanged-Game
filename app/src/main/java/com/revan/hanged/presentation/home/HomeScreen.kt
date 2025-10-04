package com.revan.hanged.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.revan.hanged.navigation.ScreenRoute
import com.revan.hanged.presentation.game_history.components.CreateRoom
import com.revan.hanged.presentation.home.components.HomeContent
import com.revan.hanged.presentation.home.components.HomeHeader
import com.revan.hanged.presentation.home.components.RoomStatusGuide
import com.revan.hanged.ui.theme.DarkGray

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    username: String,
    uiState: HomeState,
    onEvent: (HomeEvent) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = DarkGray)
            .navigationBarsPadding().then(if (uiState.isVisibleRoomStatusGuide) Modifier.blur(6.dp) else Modifier)
    ) {

        HomeHeader(
            username = username, logoutButtonClick = {
                onEvent(HomeEvent.LogOut(username = username))
            },
            navigateGameHistory = {
                onEvent(HomeEvent.OnNavigate(route = ScreenRoute.GameHistory))
            }
        )

        CreateRoom()

        HomeContent(uiState = uiState, onEvent = onEvent)

        if (uiState.isVisibleRoomStatusGuide) {
            RoomStatusGuide(onEvent = onEvent)
        }
    }
}


@Preview
@Composable
private fun HomeScreenPrev() {
    HomeScreen(uiState = HomeState(), onEvent = {}, username = "Raven")
}