package com.revan.hanged.presentation.game.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revan.hanged.R
import com.revan.hanged.domain.model.RoomInfo
import com.revan.hanged.presentation.game.GameState
import com.revan.hanged.ui.theme.LightGray

@Composable
fun PlayersModalSheetContent(
    modifier: Modifier = Modifier,
    uiState : GameState,
    roomInfo: RoomInfo
) {
    LazyColumn (
        verticalArrangement = Arrangement.spacedBy(22.dp),
        modifier = modifier.padding(start = 34.dp, end = 34.dp, bottom = 23.dp)
    ){
        item{
            Text(
                text = stringResource(R.string.players),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = LightGray,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        item {
            Text(
                text = "${roomInfo.currentPlayerCount+1}/${roomInfo.maxPlayerCount}",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = LightGray,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        items(uiState.players) {

        }


    }
}

@Preview
@Composable
private fun PlayersModalSheetContentPrev() {
    PlayersModalSheetContent(
        roomInfo = RoomInfo(
            roomId = "123",
            userId = "123",
            userName = "123",
            difficulty = "123",
            gameLanguage = com.revan.hanged.domain.GameLanguage.ENG,
            maxPlayerCount = 4,
            currentPlayerCount = 2
        ), uiState = GameState()
    )
}