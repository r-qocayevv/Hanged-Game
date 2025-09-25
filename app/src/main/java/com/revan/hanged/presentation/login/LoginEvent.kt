package com.revan.hanged.presentation.login

import com.revan.hanged.navigation.ScreenRoute

sealed interface LoginEvent {
    data class EmailChanged(val newEmail: String) : LoginEvent
    data class PasswordChanged(val newPassword: String) : LoginEvent
    object SignInWithEmail : LoginEvent
    object SignInWithAnonymously : LoginEvent
    object CheckValidation : LoginEvent
    object ChangePasswordVisibility : LoginEvent
    data class OnNavigate (val route : ScreenRoute, val popUpTo : ScreenRoute? = null) : LoginEvent
}