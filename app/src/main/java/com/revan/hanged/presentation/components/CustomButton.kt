package com.revan.hanged.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revan.hanged.ui.theme.Red
import com.revan.hanged.utils.clickWithDarkGrayRipple

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    roundedCornerShape: Dp = 10.dp,
    text: String,
    textVerticalPadding: Dp = 6.dp,
    isButtonEnabled: Boolean,
    fontWeight : FontWeight? = null,
    onClick: () -> Unit
) {

    val buttonColor by animateColorAsState(
        targetValue = if (isButtonEnabled) Red else Red.copy(alpha = 0.5f),
        label = ""
    )

    val textColor by animateColorAsState(
        targetValue = if (isButtonEnabled) Color.White else Color.White.copy(alpha = 0.5f),
        label = ""
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clickWithDarkGrayRipple(
                isButtonEnabled = isButtonEnabled,
                onClick = {
                    onClick()
                }
            )
            .background(
                color = buttonColor,
                shape = RoundedCornerShape(roundedCornerShape)
            )
            .padding(vertical = textVerticalPadding, horizontal = 10.dp)


    ) {
        Text(
            text = text,
            modifier = Modifier,
            color = textColor,
            lineHeight = 15.sp,
            fontSize = 15.sp,
            fontWeight = fontWeight
        )
    }
}

@Preview
@Composable
private fun CustomButtonPrev() {
    CustomButton(text = "Sign in", onClick = {}, isButtonEnabled = false)
}