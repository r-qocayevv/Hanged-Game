package com.revan.hanged.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revan.hanged.Toaster
import com.revan.hanged.domain.use_case.SaveUsernameToFirestoreUseCase
import com.revan.hanged.domain.use_case.SignUpUseCase
import com.revan.hanged.navigation.NavigationCommand
import com.revan.hanged.navigation.Navigator
import com.revan.hanged.navigation.ScreenRoute
import com.revan.hanged.utils.isValidEmail
import com.revan.hanged.utils.isValidPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val navigator : Navigator,
    private val toaster: Toaster,
    private val signUpUseCase: SignUpUseCase,
    private val saveUsernameToFirestoreUseCase: SaveUsernameToFirestoreUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.EmailChanged -> {
                emailChanged(event.newEmail)
            }

            is RegisterEvent.PasswordChanged -> {
                passwordChanged(event.newPassword)
            }

            is RegisterEvent.UsernameChanged -> {
                usernameChanged(event.newUsername)
            }

            RegisterEvent.SignUp -> {
                signUp()
            }

            RegisterEvent.ChangePasswordVisibility -> {
                changePasswordVisibility()
            }

            RegisterEvent.CheckValidation -> {
                checkValidation()
            }

            is RegisterEvent.OnNavigate -> {
                navigate(route = event.route, popUpTo = event.popUpTo)
            }
        }

    }

    private fun navigate(route: ScreenRoute,popUpTo: ScreenRoute? ) {
        viewModelScope.launch {
            navigator.sendNavigation(NavigationCommand.OnNavigate(route = route, popUpTo = popUpTo))
        }
    }

    private fun checkValidation() {
        val email = _state.value.email
        val password = _state.value.password
        val username = _state.value.username

        _state.update { it.copy(isButtonEnabled = (password.isValidPassword() && email.isValidEmail()) && username.isNotBlank()) }
    }

    private fun changePasswordVisibility() {
        _state.update {
            it.copy(
                isPasswordVisible = !_state.value.isPasswordVisible
            )
        }
    }

    private fun emailChanged(newEmail: String) {
        _state.update { it.copy(email = newEmail) }
    }

    private fun passwordChanged(newPassword: String) {
        _state.update { it.copy(password = newPassword) }
    }

    private fun usernameChanged(username: String) {
        _state.update { it.copy(username = username) }
    }

    private fun signUp() {
        val email = _state.value.email
        val password = _state.value.password
        val username = _state.value.username


        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                val userUid = signUpUseCase(email = email, password = password, username = username)
                if (userUid.isNotBlank()) {
                    saveUsernameToFirestore(username = username, userUid = userUid, email = email)
                    navigate(route = ScreenRoute.Login, popUpTo = ScreenRoute.Register)
                    _state.update { it.copy(isLoading = false) }
                }
            } catch (e: Exception) {
                toaster.emitToastMessage(message = e.localizedMessage ?: "Unknown error")
                _state.update { it.copy(isLoading = false) }
            }

        }
    }

    private fun saveUsernameToFirestore (username : String, userUid : String,email : String) {
        viewModelScope.launch {
            try {
                saveUsernameToFirestoreUseCase(
                    username = username,
                    userUid = userUid,
                    email = email
                )
            } catch (e: Exception) {
                toaster.emitToastMessage(message = e.localizedMessage ?: "Unknown error")
            }
        }
    }

}