package com.revan.hanged.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revan.hanged.utils.clickWithDarkGrayRipple

@Composable
fun CustomWhiteButton(
    modifier: Modifier = Modifier,
    text : String,
    textVerticalPadding : Dp = 10.dp,
    fontWeight: FontWeight? = null,
    onClick : () -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clickWithDarkGrayRipple(
                isButtonEnabled = true,
                onClick = {
                    onClick()
                }
            )
            .background(
                color = Color(0xFF2F3640),
                shape = RoundedCornerShape(15.dp)
            )
            .fillMaxHeight()
            .border(
                width = 1.dp,
                color = Color.White.copy(alpha = 0.5f),
                shape = RoundedCornerShape(15.dp)
            )
            .padding(vertical = textVerticalPadding, horizontal = 10.dp)


    ) {
        Text(
            text = text,
            modifier = Modifier,
            color = Color.White,
            fontSize = 15.sp,
            lineHeight = 15.sp,
            fontWeight = fontWeight
        )
    }
}


@Preview
@Composable
private fun CustomWhiteButtonPrev() {
    CustomWhiteButton(onClick = {}, text = "Stay")
}