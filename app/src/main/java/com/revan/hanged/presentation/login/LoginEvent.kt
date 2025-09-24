package com.revan.hanged.presentation.login

sealed interface LoginEvent {
    data class EmailChanged(val newEmail: String) : LoginEvent
    data class PasswordChanged(val newPassword: String) : LoginEvent
    object SignIn : LoginEvent
}