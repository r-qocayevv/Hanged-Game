package com.revan.hanged.data

import com.revan.hanged.domain.model.GameOver
import com.revan.hanged.domain.model.GameInfo
import com.revan.hanged.domain.model.GameSettingsUpdate
import com.revan.hanged.domain.model.GameUpdate
import com.revan.hanged.domain.model.PlayerJoined
import com.revan.hanged.domain.model.PlayerReadyUpdated
import com.revan.hanged.domain.model.RoomState
import com.revan.hanged.domain.model.Turn

sealed class GameSocketEvents {
    data class PlayerJoinedEvent(val playerJoined: PlayerJoined?) : GameSocketEvents()
    data class PlayerReadyUpdatedEvent(val playerReadyUpdated: PlayerReadyUpdated?) : GameSocketEvents()
    data class PlayerLeftEvent(val userId : String?) : GameSocketEvents()
    data class GameStartedEvent(val gameInfo: GameInfo?) : GameSocketEvents()
    data class TurnEvent (val turn : Turn?): GameSocketEvents()
    data class GameUpdateEvent(val gameUpdate: GameUpdate?) : GameSocketEvents()
    data class GameSettingsUpdateEvent(val gameSettingsUpdate: GameSettingsUpdate?) : GameSocketEvents()
    data class PlayerEliminatedEvent(val userId : String?) : GameSocketEvents()
    data class GameOverEvent(val gameOver: GameOver?) : GameSocketEvents()
    data class RoomStateEvent(val roomState : RoomState?) : GameSocketEvents()
}