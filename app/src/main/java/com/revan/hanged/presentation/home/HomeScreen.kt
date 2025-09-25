package com.revan.hanged.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.revan.hanged.presentation.home.components.HomeContent
import com.revan.hanged.presentation.home.components.HomeHeader
import com.revan.hanged.presentation.home.components.RoomStatusGuide
import com.revan.hanged.ui.theme.DarkGray

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    username : String,
    uiState: HomeState,
    onEvent: (HomeEvent) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = DarkGray)
            .padding(horizontal = 15.dp)
            .systemBarsPadding()
    ) {

        HomeHeader(username = username, logoutButtonClick = {
            onEvent(HomeEvent.LogOut)
        })

        Spacer(Modifier.height(46.dp))

        HomeContent(uiState = uiState, onEvent = onEvent)

        if (uiState.isVisibleRoomStatusGuide) {
            RoomStatusGuide(onEvent = onEvent)
        }
    }
}

@Preview
@Composable
private fun HomeScreenPrev() {
    HomeScreen(uiState = HomeState(), onEvent = {}, username =  "Raven")
}