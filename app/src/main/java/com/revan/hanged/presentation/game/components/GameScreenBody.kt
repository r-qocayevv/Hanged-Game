package com.revan.hanged.presentation.game.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.revan.hanged.presentation.game.GameState

@Composable
fun GameBody(
    modifier: Modifier = Modifier,
    uiState : GameState
) {
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(Modifier.height(25.dp))
        Hangman(wrongGuesses = uiState.wrongGuessesCount)
        Spacer(Modifier.height(25.dp))
        Word(wordLength = uiState.wordLength, discoveredLetters = uiState.discoveredLetters)
    }
}

@Preview
@Composable
private fun GameBodyPrev() {
    GameBody(uiState = GameState())
}