package com.revan.hanged.navigation

sealed interface NavigationCommand {

    data object ToBack : NavigationCommand

    data class OnNavigate (val route : ScreenRoute, val popUpTo : ScreenRoute?) : NavigationCommand

}