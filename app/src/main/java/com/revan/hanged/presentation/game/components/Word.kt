package com.revan.hanged.presentation.game.components

import android.content.res.Resources
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
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
import com.revan.hanged.ui.theme.LightGray

@Composable
fun Word(
    modifier: Modifier = Modifier,
    horizontalPadding: Dp = 40.dp,
    discoveredLetters: List<String>,
    wordLength: Int
) {
    val displayMetrics = Resources.getSystem().displayMetrics

    val screenWidthPx = displayMetrics.widthPixels
    val screenWidth = screenWidthPx / displayMetrics.density

    val screenWithWithoutPadding = screenWidth.dp - (horizontalPadding * 2)
    val ratio = screenWithWithoutPadding / ((wordLength * 2) + (wordLength - 1))
    val wordWidth = ratio * 2
    val spaceBetweenLettersWidth = ratio


    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalPadding),
        contentAlignment = Alignment.Center
    ) {

        LazyRow(
            verticalAlignment = Alignment.Bottom
        ) {
            if (discoveredLetters.isEmpty()) {
                items(wordLength) {
                    Column(
                        modifier = Modifier.width(wordWidth),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        BoxWithConstraints(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {

                            val fontSize = maxWidth.value * 0.9f
                            Text(
                                text = "",
                                fontSize = fontSize.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White,
                                maxLines = 1
                            )
                        }

                        Spacer(Modifier.height(4.dp))

                        HorizontalDivider(
                            modifier = Modifier.fillMaxWidth(),
                            thickness = 2.dp,
                            color = LightGray
                        )
                    }

                    Spacer(Modifier.width(spaceBetweenLettersWidth))

                }


            } else {
                items(discoveredLetters) { letter ->
                    Column(
                        modifier = Modifier.width(wordWidth),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        BoxWithConstraints(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            val fontSize = maxWidth.value * 0.9f
                            Text(
                                text = if (letter != "_") letter.uppercase() else "",
                                fontSize = fontSize.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White,
                                maxLines = 1
                            )
                        }

                        Spacer(Modifier.height(4.dp))

                        HorizontalDivider(
                            modifier = Modifier.fillMaxWidth(),
                            thickness = 2.dp,
                            color = LightGray
                        )
                    }

                    Spacer(Modifier.width(spaceBetweenLettersWidth))
                }
            }
        }
    }
}


@Preview
@Composable
private fun WordPrev() {
    Word(
        wordLength = 10,
        discoveredLetters = emptyList()
    )
}