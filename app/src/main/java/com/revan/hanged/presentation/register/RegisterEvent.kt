package com.revan.hanged.presentation.register

import com.revan.hanged.navigation.ScreenRoute


sealed interface RegisterEvent {
    data class UsernameChanged (val newUsername: String) : RegisterEvent
    data class EmailChanged(val newEmail: String) : RegisterEvent
    data class PasswordChanged(val newPassword: String) : RegisterEvent
    object ChangePasswordVisibility : RegisterEvent
    object CheckValidation : RegisterEvent
    object SignUp : RegisterEvent
    data class OnNavigate (val route : ScreenRoute, val popUpTo : ScreenRoute? = null) : RegisterEvent
}