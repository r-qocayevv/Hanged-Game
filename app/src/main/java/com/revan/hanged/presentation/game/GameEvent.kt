package com.revan.hanged.presentation.game

import com.revan.hanged.domain.model.RoomInfo

sealed interface GameEvent {

    data class ChangeReadyState(val roomInfo: RoomInfo) : GameEvent
    data class LeaveRoom(val roomInfo: RoomInfo) : GameEvent
    data class OnChangeGuessWord(val newGuessWord : String) : GameEvent
    data class KeyClicked (val key : Char,val roomInfo: RoomInfo) : GameEvent
    object OnOpenGuessWord : GameEvent
    data class OnGuessWord (val roomInfo: RoomInfo) : GameEvent
    object ChangePlayerBottomSheetState : GameEvent
    object ChangeConfirmExitBottomSheetState : GameEvent
    data object ChangeWordVisibility : GameEvent

}