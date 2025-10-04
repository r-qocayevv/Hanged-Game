package com.revan.hanged.domain.repository

import com.revan.hanged.domain.model.Game
import com.revan.hanged.domain.model.Leaderboard
import com.revan.hanged.domain.model.User

interface HangedRepository {
    suspend fun getGames () : List<Game>
    suspend fun getLeaderboard () : List<Leaderboard>
    suspend fun getGameDetail (gameId: String) : Game
    suspend fun getUserData (userId: String) : User?
}