package com.revan.hanged.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class ScreenRoute {

    @Serializable
    data object Login : ScreenRoute()

    @Serializable
    data object Register : ScreenRoute()

    @Serializable
    data class Home(val username : String) : ScreenRoute()

}