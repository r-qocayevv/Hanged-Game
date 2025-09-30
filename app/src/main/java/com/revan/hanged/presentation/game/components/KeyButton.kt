package com.revan.hanged.presentation.game.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revan.hanged.ui.theme.Red
import com.revan.hanged.utils.clickWithoutRipple

@Composable
fun KeyButton(
    letter: Char,
    keyWidth: Dp,
    keyColor : Color?,
    isCanGuessWordOpen : Boolean,
    isYourTurn : Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .alpha(if (isYourTurn || isCanGuessWordOpen) 1f else 0.5f)
            .width(keyWidth)
            .background(color = keyColor ?: Color(0xFF2E3740), shape = RoundedCornerShape(10.dp))
            .padding(11.dp)
            .clickWithoutRipple(isEnabled = isYourTurn || isCanGuessWordOpen,onClick = {
                onClick()
            })
            ,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = letter.toString(),
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
        )
    }
}


@Preview
@Composable
fun KeyButtonPrev() {
    KeyButton(
        letter = 'A',
        keyWidth = 50.dp,
        isYourTurn = true,
        isCanGuessWordOpen = true,
        keyColor = Red,
        onClick = {}

    )
}