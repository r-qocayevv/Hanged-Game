package com.revan.hanged.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.revan.hanged.data.dto.GameDTO
import com.revan.hanged.data.dto.RoomsDTO
import com.revan.hanged.data.dto.toGame
import com.revan.hanged.data.dto.toRooms
import com.revan.hanged.domain.FirebaseRepository
import com.revan.hanged.domain.model.Game
import com.revan.hanged.domain.model.Room
import kotlinx.coroutines.tasks.await

class FirebaseRepositoryImpl(
    private val firestore : FirebaseFirestore
) : FirebaseRepository {
    override suspend fun getRooms(): List<Room> {
        val snapshot = firestore.collection("rooms").get().await()
        val roomDto = snapshot.toObjects(RoomsDTO::class.java)
        return roomDto.map { it.toRooms() }

    }

    override suspend fun getGames(): List<Game> {
        val snapshot = firestore.collection("games").get().await()
        val gameDTO =snapshot.toObjects(GameDTO::class.java)
        return gameDTO.map { it.toGame() }

    }

}