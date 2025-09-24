package com.revan.hanged.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.revan.hanged.R
import com.revan.hanged.ui.theme.LightGray
import com.revan.hanged.ui.theme.Typography

@Composable
fun CustomHangedText(
    modifier: Modifier = Modifier,
    size : TextUnit
) {
    Text(
        modifier = modifier,
        textAlign = TextAlign.Center,
        text = stringResource(R.string.hanged),
        color = LightGray,
        fontSize = size,
        fontFamily = FontFamily(Font(R.font.inknut_antiqua_semibold)),
    )
}

@Preview
@Composable
private fun CustomHangedTextPrev() {
    CustomHangedText(size = 15.sp)
}