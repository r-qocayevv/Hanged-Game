package com.revan.hanged.presentation.game.components

import android.content.res.Resources
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.revan.hanged.domain.GameLanguage

@Composable
fun Keyboard(
    modifier: Modifier = Modifier,
    keyColors: List<Pair<String, Color>>,
    layout: GameLanguage,
    horizontalPadding: Dp = 9.dp,
    spaceBetweenKeys: Dp = 4.dp,
    keyCountInRow: Int = 10,
    isCanGuessWordOpen: Boolean,
    isYourTurn: Boolean,
    onKeyClick: (Char) -> Unit
) {
    val displayMetrics = Resources.getSystem().displayMetrics

    val screenWidthPx = displayMetrics.widthPixels
    val screenWidth = screenWidthPx / displayMetrics.density

    val keyWidth =
        (screenWidth.dp - (horizontalPadding * 2) - 9 * (spaceBetweenKeys)) / keyCountInRow

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalPadding),
        verticalArrangement = Arrangement.spacedBy(9.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        layout.keys.forEach { row ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(spaceBetweenKeys)
            ) {
                row.forEach { key ->
                    KeyButton(
                        keyWidth = keyWidth,
                        letter = key,
                        keyColor = keyColors.find { it.first.equals(key.toString(), ignoreCase = true) }?.second,
                        isCanGuessWordOpen = isCanGuessWordOpen,
                        isYourTurn = isYourTurn,
                        onClick = {
                            onKeyClick(key)
                        }
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun KeyboardPrev() {
    Keyboard(
        layout = GameLanguage.AZE,
        isYourTurn = true,
        keyColors = emptyList(),
        isCanGuessWordOpen = true,
        onKeyClick = {})
}