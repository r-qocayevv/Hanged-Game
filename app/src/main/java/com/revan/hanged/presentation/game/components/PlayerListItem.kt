package com.revan.hanged.presentation.game.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revan.hanged.domain.model.Player
import com.revan.hanged.ui.theme.LightGray
import com.revan.hanged.ui.theme.Red

@Composable
fun PlayerListItem(
    modifier: Modifier = Modifier,
    player : Player
) {
    Row (
        modifier= modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = player.name,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            color = LightGray,
        )
        Text(
            text = if (player.ready) "Ready" else "Not ready",
            color = if (player.ready) Color(0xFF3EB481) else Red,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp
        )
    }
}

@Preview
@Composable
private fun PlayerListItemPrev() {
    PlayerListItem(player = Player(
        eliminated = false,
        id = "1",
        ready = true,
        name = "Revan",
        score = 2
    ))
}