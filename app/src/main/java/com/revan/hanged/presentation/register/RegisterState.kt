package com.revan.hanged.presentation.register

data class RegisterState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val isPasswordVisible : Boolean = false,
    val isButtonEnabled: Boolean = false
)
