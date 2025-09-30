package com.revan.hanged.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStorePreferencesRepository {

    fun getUsername(): Flow<String?>
    suspend fun saveUsername(username: String)
    fun getUserId(): Flow<String?>
    suspend fun saveUserId(userId: String)
    fun isLoggedIn(): Flow<Boolean>
    suspend fun saveLoginState(isLoggedIn: Boolean)
    suspend fun clearAllUserDataFromLocal ()
}