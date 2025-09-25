package com.revan.hanged.domain

import com.revan.hanged.domain.model.Game
import com.revan.hanged.domain.model.Room

interface FirebaseRepository {
    suspend fun getRooms () : List<Room>
    suspend fun getGames () : List<Game>
    suspend fun signUp (email : String, password : String) : String
    suspend fun signInWithEmail (email : String, password : String) : String
    suspend fun signInWithAnonymously () : String
    suspend fun saveUsernameToFirestore (username : String, userUid : String,email: String)
    suspend fun getUsernameFromFirestore (userUid : String) : String
    fun logOut ()
}