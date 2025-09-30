package com.revan.hanged.domain

enum class GameLanguage(val language: String, val keys: List<List<Char>>) {
    ENG(

        keys = listOf(
            listOf('Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P'),
            listOf('A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Z'),
            listOf('X', 'C', 'V', 'B', 'N', 'M')
        ),
        language = "en"
    ),
    AZE(
        keys = listOf(
            listOf('Q', 'Ü', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P'),
            listOf('A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Z'),
            listOf('X', 'C', 'V', 'B', 'N', 'M', 'Ö', 'Ğ', 'İ', 'Ə'),
            listOf('Ç', 'Ş')
        ),
        language = "az"
    ),
    TRY(
        keys = listOf(
            listOf('F', 'J', 'I', 'E', 'R', 'T', 'Y', 'U', 'O', 'P', 'Ğ', 'Ü'),
            listOf('A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Ş', 'İ'),
            listOf('Z', 'C', 'V', 'B', 'N', 'M', 'Ö', 'Ç')
        ),
        language = "tr"
    )
}