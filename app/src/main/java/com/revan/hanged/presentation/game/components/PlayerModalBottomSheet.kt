package com.revan.hanged.presentation.game.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revan.hanged.presentation.game.GameState
import com.revan.hanged.ui.theme.LightGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerModalBottomSheet(
    modifier: Modifier = Modifier,
    maxPlayerCount : Int,
    uiState: GameState,
    onDismiss: () -> Unit
) {

    ModalBottomSheet(
        containerColor = Color(0xFF2E3740),
        modifier = modifier.padding(horizontal = 20.dp).navigationBarsPadding(),
        onDismissRequest = {
            onDismiss()
        }
    ) {
        PlayerModalBottomSheetContent(uiState = uiState, maxPlayerCount = maxPlayerCount)
    }
}

@Composable
fun PlayerModalBottomSheetContent(
    modifier: Modifier = Modifier,
    maxPlayerCount: Int,
    uiState : GameState,
) {
    LazyColumn (
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        item {
            Text(
                text = "Players",
                color = LightGray,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
        }

        item {
            Text(
                text = "${uiState.players.size}/${maxPlayerCount}",
                color = LightGray,
                fontSize = 10.sp,
            )
        }

        items(uiState.players) {
            PlayerListItem(player = it)
        }
    }
}


@Preview
@Composable
private fun PlayerModalBottomSheetPrev() {
    PlayerModalBottomSheetContent(uiState = GameState(), maxPlayerCount = 5)
}