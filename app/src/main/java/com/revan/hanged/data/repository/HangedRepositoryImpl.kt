package com.revan.hanged.data.repository

import android.util.Log
import com.revan.hanged.data.remote.HangedAPI
import com.revan.hanged.data.remote.dto.leaderboard.toLeaderboard
import com.revan.hanged.data.remote.dto.toGame
import com.revan.hanged.data.remote.dto.user_detail.toUser
import com.revan.hanged.domain.model.Game
import com.revan.hanged.domain.model.Leaderboard
import com.revan.hanged.domain.model.User
import com.revan.hanged.domain.repository.HangedRepository

class HangedRepositoryImpl(
    private val hangedApi : HangedAPI
) : HangedRepository {


    override suspend fun getGames(): List<Game> {
        val gamesDto = hangedApi.getGames()
        Log.d("HangedRepositoryImpl", gamesDto.toString())
        if (gamesDto.success) {
            return gamesDto.data.games.map { it.toGame() }
        } else {
            throw Exception("Failed to fetch games")
        }
    }

    override suspend fun getLeaderboard(): List<Leaderboard> {
        val leaderboardListDto = hangedApi.getLeaderboard()
        Log.d("HangedRepositoryImpl", leaderboardListDto.toString())
        if (leaderboardListDto.success) {
            return leaderboardListDto.data.leaderboard.map { it.toLeaderboard() }
        } else {
            throw Exception("Failed to fetch leaderboard")
        }
    }

    override suspend fun getGameDetail(gameId: String): Game {
        val gameDetailDto = hangedApi.getGameDetail(gameId)
        Log.d("HangedRepositoryImpl", gameDetailDto.toString())
        if (gameDetailDto.success) {
            return gameDetailDto.data.game.toGame()
        } else {
            throw Exception("Failed to fetch game detail")
        }
    }

    override suspend fun getUserData(userId: String): User? {
        val userDetailDto = hangedApi.getUserData(userId)
        Log.d("HangedRepositoryImpl", userDetailDto.toString())
        return if (userDetailDto.success) {
            userDetailDto.data.user!!.toUser()
        } else {
            null
        }

    }
}