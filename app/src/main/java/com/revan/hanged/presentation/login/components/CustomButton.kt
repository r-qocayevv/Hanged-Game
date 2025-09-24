package com.revan.hanged.presentation.login.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revan.hanged.ui.theme.Red

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    text : String,
    textPadding : Dp = 2.dp,
    isButtonEnabled : Boolean,
    onClick : () -> Unit

) {
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isButtonEnabled) Red else Red.copy(alpha = 0.5f)
        )
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(vertical = textPadding),
            color = if (isButtonEnabled) Color.White else Color.White.copy(alpha = 0.5f),
            fontSize = 15.sp,
        )
    }
}

@Preview
@Composable
private fun CustomButtonPrev() {
    CustomButton(text = "Sign in", onClick = {}, isButtonEnabled = false, textPadding = 0.dp)
}