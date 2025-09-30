package com.revan.hanged.navigation

import com.revan.hanged.domain.model.RoomInfo
import kotlinx.serialization.Serializable

@Serializable
sealed class ScreenRoute {

    @Serializable
    data object Login : ScreenRoute()

    @Serializable
    data object Register : ScreenRoute()

    @Serializable
    data class Home(val username : String) : ScreenRoute()

    @Serializable
    data class Game (val roomInfo : RoomInfo) : ScreenRoute()

    @Serializable
    data object SplashScreen : ScreenRoute()

}