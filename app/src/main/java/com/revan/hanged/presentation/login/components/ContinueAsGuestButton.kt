package com.revan.hanged.presentation.login.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.revan.hanged.R
import com.revan.hanged.ui.theme.LightGray
import com.revan.hanged.ui.theme.Red
import com.revan.hanged.utils.clickWithDarkGrayRipple

@Composable
fun ContinueAsGuestButton(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.Transparent, shape = RoundedCornerShape(10.dp))
            .border(
                BorderStroke(width = 1.dp, color = LightGray),
                shape = RoundedCornerShape(10.dp)
            )
            .clickWithDarkGrayRipple(onClick = {
                onClick()
            }),
        contentAlignment = Alignment.Center
    ) {
        AnimatedContent(targetState = isLoading) { isLoading ->
            if (isLoading) {
                CircularProgressIndicator(
                    color = Red,
                    modifier = Modifier
                        .padding(vertical = 15.dp)
                        .size(15.dp)
                )
            } else {
                Text(
                    modifier = Modifier.padding(vertical = 15.dp),
                    text = stringResource(R.string.continue_as_guest),
                    color = LightGray,
                )
            }
        }
    }
}

@Preview
@Composable
private fun ContinueAsGuestButtonPrev() {
    ContinueAsGuestButton(onClick = {}, isLoading = true)
}