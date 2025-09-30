package com.revan.hanged.data.repository

import com.revan.hanged.data.local.HangedDataStore
import com.revan.hanged.domain.repository.DataStorePreferencesRepository
import kotlinx.coroutines.flow.Flow

class DataStorePreferencesRepositoryImpl(
    private val dataStore: HangedDataStore
) : DataStorePreferencesRepository {
    override fun getUsername(): Flow<String?> {
        return dataStore.getUsername()
    }

    override suspend fun saveUsername(username: String) {
        dataStore.saveUsername(username = username)
    }

    override fun getUserId(): Flow<String?> {
        return dataStore.getUserId()
    }

    override suspend fun saveUserId(userId: String) {
        dataStore.saveUserId(userId = userId)
    }

    override fun isLoggedIn(): Flow<Boolean> = dataStore.isLoggedIn()
    override suspend fun saveLoginState(isLoggedIn: Boolean) =
        dataStore.saveLoginState(isLoggedIn)

    override suspend fun clearAllUserDataFromLocal() {
        dataStore.clearAllUserDataFromLocal()
    }

}