package com.revan.hanged.domain

import com.revan.hanged.domain.model.Game
import com.revan.hanged.domain.model.Room

interface FirebaseRepository {
    suspend fun getRooms () : List<Room>
    suspend fun getGames () : List<Game>
}