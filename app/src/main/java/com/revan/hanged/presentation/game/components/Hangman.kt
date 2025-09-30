package com.revan.hanged.presentation.game.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.revan.hanged.R

@Composable
fun Hangman(
    wrongGuesses: Int,
    modifier: Modifier = Modifier
) {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.ripple_animation)
    )

    Box(
        modifier = modifier
            .padding(horizontal = 32.dp)
            .background(
                color = Color(0xFF2E3740),
                shape = RoundedCornerShape(16.dp),
            )
            .padding(horizontal = 64.dp)
    ) {
        if (wrongGuesses == 0) {
            LottieAnimation(
                modifier = Modifier
                    .aspectRatio(1f)
                    .padding(vertical = 13.dp),
                composition = composition,
                iterations = LottieConstants.IterateForever
            )
        } else {
            HangmanCanvas(wrongGuesses)
        }
    }
}

@Preview
@Composable
private fun HangmanPrev() {
    Hangman(wrongGuesses = 10)
}