package com.revan.hanged.data.local

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.revan.hanged.utils.Constants.HANGED_PREFS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name  = HANGED_PREFS)

class HangedDataStore(private val context: Context) {

    companion object {
        private val USERNAME = stringPreferencesKey("username")
        private val USER_ID = stringPreferencesKey("user_id")
        private val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
    }

    fun getUsername(): Flow<String?> =
        context.dataStore.data.map { prefs -> prefs[USERNAME] }

    suspend fun saveUsername(username: String) {
        context.dataStore.edit { prefs -> prefs[USERNAME] = username }
    }

    fun getUserId () : Flow<String?> =
        context.dataStore.data.map { prefs -> prefs[USER_ID] }

    suspend fun saveUserId (userId : String) {
        context.dataStore.edit { prefs -> prefs[USER_ID] = userId }
    }

    fun isLoggedIn(): Flow<Boolean> =
        context.dataStore.data.map { prefs -> prefs[IS_LOGGED_IN] ?: false }

    suspend fun saveLoginState(isLoggedIn: Boolean) {
        context.dataStore.edit { prefs -> prefs[IS_LOGGED_IN] = isLoggedIn }
    }

    suspend fun clearAllUserDataFromLocal () {
        context.dataStore.edit { prefs ->
            prefs.remove(USERNAME)
            prefs.remove(USER_ID)
            prefs.remove(IS_LOGGED_IN)
        }
    }

}