package com.revan.hanged.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revan.hanged.domain.use_case.GetUsernameFomFirestoreUseCase
import com.revan.hanged.domain.use_case.IsLoggedInUseCase
import com.revan.hanged.domain.use_case.SaveLoginStateUseCase
import com.revan.hanged.domain.use_case.SaveUserIdToLocalUseCase
import com.revan.hanged.domain.use_case.SaveUsernameToLocalUseCase
import com.revan.hanged.domain.use_case.SignInWithAnonymouslyUseCase
import com.revan.hanged.domain.use_case.SignInWithEmailUseCase
import com.revan.hanged.navigation.NavigationCommand
import com.revan.hanged.navigation.Navigator
import com.revan.hanged.navigation.ScreenRoute
import com.revan.hanged.utils.isValidEmail
import com.revan.hanged.utils.isValidPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val navigator: Navigator,
    private val signInWithEmailUseCase: SignInWithEmailUseCase,
    private val signInWithAnonymouslyUseCase: SignInWithAnonymouslyUseCase,
    private val getUsernameFromFirestoreUseCase: GetUsernameFomFirestoreUseCase,
    private val saveUsernameToLocalUseCase: SaveUsernameToLocalUseCase,
    private val saveUserIdToLocalUseCase: SaveUserIdToLocalUseCase,
    private val saveLoginStateUseCase: SaveLoginStateUseCase,
    private val isLoggedInUseCase: IsLoggedInUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    init {
        isLoggedIn()
    }

    private fun isLoggedIn() {
        viewModelScope.launch {
            isLoggedInUseCase().collectLatest {
                _state.update {
                    it.copy(isLoggedIn = it.isLoggedIn)
                }
            }
        }
    }

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged -> {
                emailChanged(event.newEmail)
            }

            is LoginEvent.PasswordChanged -> {
                passwordChanged(event.newPassword)
            }

            LoginEvent.SignInWithEmail -> {
                signInWithEmail()
            }

            LoginEvent.SignInWithAnonymously -> {
                signInWithAnonymously()
            }

            LoginEvent.CheckValidation -> {
                checkValidation()
            }

            LoginEvent.ChangePasswordVisibility -> {
                changePasswordVisibility()
            }

            is LoginEvent.OnNavigate -> {
                navigate(event.route, event.popUpTo)
            }
        }
    }

    private fun navigate(route: ScreenRoute,popUpTo: ScreenRoute?) {
        viewModelScope.launch {
            navigator.sendNavigation(NavigationCommand.OnNavigate(route = route, popUpTo = popUpTo ))
        }
    }

    private fun changePasswordVisibility() {
        _state.update {
            it.copy(
                isPasswordVisible = !_state.value.isPasswordVisible
            )
        }
    }

    private fun checkValidation() {
        val email = _state.value.email
        val password = _state.value.password

        _state.update { it.copy(isButtonEnabled = (password.isValidPassword() && email.isValidEmail())) }
    }

    private fun emailChanged(newEmail: String) {
        _state.update { it.copy(email = newEmail) }
    }

    private fun signInWithAnonymously() {
        viewModelScope.launch {
            val userUid = signInWithAnonymouslyUseCase()
            navigate(route = ScreenRoute.Home(username = "Guest"), popUpTo = ScreenRoute.Login)
            saveUserInfoToLocal(username = "Guest", userUid = userUid, isLoggedIn = false)
        }
    }

    private fun passwordChanged(newPassword: String) {
        _state.update { it.copy(password = newPassword) }
    }

    private fun saveUserInfoToLocal(username: String, userUid: String, isLoggedIn: Boolean = true) {
        viewModelScope.launch {
            saveUsernameToLocalUseCase(username = username)
            saveUserIdToLocalUseCase(userId = userUid)
            saveLoginStateUseCase(isLoggedIn = isLoggedIn)
        }
    }

    private fun signInWithEmail() {
        val email = _state.value.email
        val password = _state.value.password

        viewModelScope.launch {
            val userUid = signInWithEmailUseCase(email = email, password = password)
            val username = getUsernameFromFirestoreUseCase(userUid = userUid)
            navigate(route = ScreenRoute.Home(username = username), popUpTo = ScreenRoute.Login)
            saveUserInfoToLocal(username = username,userUid = userUid)
        }
    }




}