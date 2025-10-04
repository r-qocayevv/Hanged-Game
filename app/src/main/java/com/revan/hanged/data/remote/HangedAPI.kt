package com.revan.hanged.data.remote

import com.revan.hanged.data.remote.dto.game_detail.GameDetailDTO
import com.revan.hanged.data.remote.dto.games.GamesDTO
import com.revan.hanged.data.remote.dto.leaderboard.LeaderboardListDTO
import com.revan.hanged.data.remote.dto.user_detail.UserDetailDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface HangedAPI {

    @GET("/games")
    suspend fun getGames(): GamesDTO

    @GET("/leaderboard")
    suspend fun getLeaderboard(): LeaderboardListDTO

    @GET("/games/{gameId}")
    suspend fun getGameDetail(@Path("gameId") gameId: String): GameDetailDTO

    @GET("/users/{userId}")
    suspend fun getUserData(@Path("userId") userId: String): UserDetailDTO

}