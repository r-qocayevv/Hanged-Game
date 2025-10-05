package com.revan.hanged.presentation.login

data class LoginState (
    //todo loading qoy
    val email : String = "",
    val password : String = "",
    val isPasswordVisible : Boolean = false,
    val isButtonEnabled : Boolean = false,
    val isLoggedIn : Boolean = false
)