package com.revan.hanged.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.revan.hanged.data.remote.dto.GameDTO
import com.revan.hanged.data.remote.dto.RoomsDTO
import com.revan.hanged.data.remote.dto.toGame
import com.revan.hanged.data.remote.dto.toRooms
import com.revan.hanged.domain.model.Game
import com.revan.hanged.domain.model.Room
import com.revan.hanged.domain.repository.FirebaseRepository
import com.revan.hanged.utils.Constants.FIREBASE_EMAIL
import com.revan.hanged.utils.Constants.FIREBASE_GAME_COLLECTION
import com.revan.hanged.utils.Constants.FIREBASE_ROOM_COLLECTION
import com.revan.hanged.utils.Constants.FIREBASE_UID
import com.revan.hanged.utils.Constants.FIREBASE_USERNAME
import com.revan.hanged.utils.Constants.FIREBASE_USER_COLLECTION
import kotlinx.coroutines.tasks.await

class FirebaseRepositoryImpl(
    private val firestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
) : FirebaseRepository {
    override suspend fun getRooms(): List<Room> {
        val snapshot = firestore.collection(FIREBASE_ROOM_COLLECTION).get().await()
        val roomDto = snapshot.toObjects(RoomsDTO::class.java)
        return roomDto.map { it.toRooms() }.sortedBy { it.status }

    }

    override suspend fun getGames(): List<Game> {
        val snapshot = firestore.collection(FIREBASE_GAME_COLLECTION).get().await()
        val gameDTO = snapshot.toObjects(GameDTO::class.java)
        return gameDTO.map { it.toGame() }

    }

    override suspend fun signUp(
        email: String,
        password: String
    ): String {
        return try {
            val result = firebaseAuth
                .createUserWithEmailAndPassword(email, password)
                .await()
            val user = result.user
            user?.uid ?: throw Exception("UserDTO not found")
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun signInWithEmail(email: String, password: String): String {
        return try {
            val result = firebaseAuth
                .signInWithEmailAndPassword(email, password)
                .await()
            val user = result.user
            user?.uid ?: throw Exception("UserDTO not found")
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun signInWithAnonymously(): String {
        return try {
            val result = firebaseAuth
                .signInAnonymously()
                .await()
            val user = result.user
            user?.uid ?: throw Exception("UserDTO not found")
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun saveUsernameToFirestore(username: String, userUid: String, email: String) {
        try {
            firestore
                .collection(FIREBASE_USER_COLLECTION)
                .document(userUid)
                .set(
                    mapOf(
                        FIREBASE_USERNAME to username,
                        FIREBASE_EMAIL to email,
                        FIREBASE_UID to userUid
                    )
                )
                .await()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override suspend fun getUsernameFromFirestore(userUid: String): String {
        return try {
            val userInfo = firestore
                .collection(FIREBASE_USER_COLLECTION)
                .document(userUid)
                .get().await()

            userInfo.getString(FIREBASE_USERNAME) ?: throw Exception("Username not found")
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    override fun logOut() {
        firebaseAuth.signOut()
    }

}