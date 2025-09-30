package com.revan.hanged.domain.model

data class GameUpdate (
    val discovered: List<String>? = null,
    val guessedBy : String,
    val guessedLetter : String,
    val correct : Boolean,
    val playerScore : Int,
    val wrongGuesses : Int? = null
)