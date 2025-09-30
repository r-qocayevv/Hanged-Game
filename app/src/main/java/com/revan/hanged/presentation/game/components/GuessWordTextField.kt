package com.revan.hanged.presentation.game.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revan.hanged.R
import com.revan.hanged.domain.GameLanguage
import com.revan.hanged.domain.model.RoomInfo
import com.revan.hanged.presentation.game.GameEvent
import com.revan.hanged.presentation.game.GameState
import com.revan.hanged.ui.theme.Red

@Composable
fun GuestWordTextField(
    modifier: Modifier = Modifier,
    roomInfo: RoomInfo,
    uiState: GameState,
    onEvent: (GameEvent) -> Unit
) {
    TextField(
        shape = RoundedCornerShape(15.dp),
        singleLine = true,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color(0xFF382A2A),
            focusedContainerColor = Color(0xFF382A2A),
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            cursorColor = Color.White
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Send
        ),
        keyboardActions = KeyboardActions(
            onSend = {
                onEvent(GameEvent.OnGuessWord(roomInfo = roomInfo))
            }
        ),
        modifier = modifier

            .background(
                color = Color(0xFF382A2A),
                shape = RoundedCornerShape(15.dp)
            )
            .border(
                border = BorderStroke(width = 1.dp, color = Red),
                shape = RoundedCornerShape(15.dp)
            ),
        value = uiState.guessWord,
        onValueChange = { newGuessWord ->
            onEvent(GameEvent.OnChangeGuessWord(newGuessWord = newGuessWord))
        },
        placeholder = {
            Text(
                text = stringResource(R.string.custom_word_here),
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }
    )


}

@Preview
@Composable
private fun GuessWordTextFieldPrev() {
    GuestWordTextField(
        uiState = GameState(), onEvent = {},
        roomInfo = RoomInfo(
            roomId = "",
            userId = "",
            userName = "",
            difficulty = "",
            gameLanguage = GameLanguage.ENG,
            maxPlayerCount = 0,
            currentPlayerCount = 0
            )
    )

}