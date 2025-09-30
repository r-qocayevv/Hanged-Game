package com.revan.hanged.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revan.hanged.domain.use_case.GetUsernameFromLocalUseCase
import com.revan.hanged.domain.use_case.IsLoggedInUseCase
import com.revan.hanged.navigation.NavigationCommand
import com.revan.hanged.navigation.Navigator
import com.revan.hanged.navigation.ScreenRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val navigator: Navigator,
    private val isLoggedInUseCase: IsLoggedInUseCase,
    private val getUsernameFromLocalUseCase: GetUsernameFromLocalUseCase,
) : ViewModel() {

    init {
        isLoggedIn()
    }

    private fun isLoggedIn() {
        viewModelScope.launch {
            delay(2000)
            isLoggedInUseCase().collect {
                if (it) {
                    getUsernameFromLocalUseCase().collect {username ->
                        username?.let {safeUsername ->
                            navigate(route = ScreenRoute.Home(safeUsername), popUpTo = ScreenRoute.SplashScreen)
                        }
                    }
                } else {
                    navigate(route = ScreenRoute.Login, popUpTo = ScreenRoute.SplashScreen)
                }
            }
        }
    }

    private fun navigate(route: ScreenRoute, popUpTo: ScreenRoute? = null) {
        viewModelScope.launch {
            navigator.sendNavigation(NavigationCommand.OnNavigate(route = route, popUpTo))
        }
    }


}