package com.revan.hanged.presentation.game_history

import com.revan.hanged.domain.model.Game

sealed interface GameHistoryEvent {
    data object ToBack  : GameHistoryEvent
    data class OnTabSelected(val index: Int) : GameHistoryEvent
    data class OnGameSelected (val game : Game) : GameHistoryEvent
    data object CloseGameDetailBottomSheet : GameHistoryEvent
    data object ChangeWordVisibility : GameHistoryEvent
}