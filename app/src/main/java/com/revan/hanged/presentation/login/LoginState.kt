package com.revan.hanged.presentation.login

data class LoginState (
    val email : String = "",
    val password : String = "",
    val isPasswordVisible : Boolean = false,
    val isButtonEnabled : Boolean = false
)