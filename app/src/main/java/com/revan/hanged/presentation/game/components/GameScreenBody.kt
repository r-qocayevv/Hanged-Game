package com.revan.hanged.presentation.game.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.revan.hanged.presentation.game.GameState
import com.revan.hanged.ui.theme.LightGray
import com.revan.hanged.ui.theme.Red

@Composable
fun GameBody(
    modifier: Modifier = Modifier, uiState: GameState
) {
    Column(
        modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(Modifier.height(25.dp))
        Hangman(wrongGuesses = uiState.wrongGuessesCount)
        Spacer(Modifier.height(25.dp))
        Word(wordLength = uiState.wordLength, discoveredLetters = uiState.discoveredLetters)
        Spacer(Modifier.height(20.dp))
        AnimatedContent(
            targetState = uiState.playerGuessedKey, transitionSpec = {
                slideInVertically() + fadeIn(tween(1000)) togetherWith slideOutVertically(
                    targetOffsetY = { fullHeight -> fullHeight }) + fadeOut(
                    animationSpec = tween(500)
                )
            }) { playerGuessedKey ->
            playerGuessedKey?.let {
                Text(
                    text = buildAnnotatedString {
                        withStyle(SpanStyle(color = LightGray)) {
                            append("${it.first} guessed")
                        }
                        withStyle(SpanStyle(color = if (it.third) Color(0xFF305A30) else Red)) {
                            append(" ${it.second}")
                        }
                    })
            }
        }
    }
}

@Preview
@Composable
private fun GameBodyPrev() {
    GameBody(uiState = GameState())
}