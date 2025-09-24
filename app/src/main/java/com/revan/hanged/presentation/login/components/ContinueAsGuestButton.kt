package com.revan.hanged.presentation.login.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.revan.hanged.R
import com.revan.hanged.ui.theme.LightGray

@Composable
fun ContinueAsGuestButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        border = BorderStroke(width = 1.dp, color = LightGray),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(10.dp),
        onClick = {
            onClick()
        }) {
        Text(
            modifier = Modifier.padding(vertical = 2.dp),
            text = stringResource(R.string.continue_as_guest),
            color = LightGray,
        )
    }
}

@Preview
@Composable
private fun ContinueAsGuestButtonPrev() {
    ContinueAsGuestButton(onClick = {})
}