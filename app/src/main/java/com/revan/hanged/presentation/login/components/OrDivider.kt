package com.revan.hanged.presentation.login.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revan.hanged.R
import com.revan.hanged.ui.theme.LightGray

@Composable
fun OrDivider(modifier: Modifier = Modifier) {
    Row (
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 1.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        HorizontalDivider(
            thickness = 1.dp,
            modifier = Modifier.weight(1f),
            color = LightGray
        )
        Text(
            modifier = Modifier.padding(horizontal = 10.dp),
            text = stringResource(R.string.or),
            fontSize = 14.sp,
            color = LightGray,
            fontFamily = FontFamily(Font(R.font.poppins))
        )
        HorizontalDivider(
            thickness = 1.dp,
            modifier = Modifier.weight(1f),
            color = LightGray
        )
    }
}

@Preview
@Composable
private fun OrDividerPrev() {
    OrDivider()
}