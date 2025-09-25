package com.revan.hanged.navigation

import androidx.compose.foundation.layout.imePadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.revan.hanged.presentation.home.HomeScreen
import com.revan.hanged.presentation.home.HomeViewModel
import com.revan.hanged.presentation.login.LoginScreen
import com.revan.hanged.presentation.login.LoginViewModel
import com.revan.hanged.presentation.register.RegisterScreen
import com.revan.hanged.presentation.register.RegisterViewModel

@Composable
fun NavGraph(navController: NavHostController) {


    NavHost(
        navController = navController,
        startDestination = ScreenRoute.Login
    ) {
        composable<ScreenRoute.Login>() {
            val loginViewModel = hiltViewModel<LoginViewModel>()
            val uiState by loginViewModel.state.collectAsStateWithLifecycle()
            LoginScreen(uiState = uiState, onEvent = loginViewModel::onEvent)
        }

        composable<ScreenRoute.Register> {
            val registerViewModel = hiltViewModel<RegisterViewModel>()
            val uiState by registerViewModel.state.collectAsStateWithLifecycle()
            RegisterScreen(uiState = uiState, onEvent = registerViewModel::onEvent)
        }

        composable<ScreenRoute.Home> {
            val args = it.toRoute<ScreenRoute.Home>()
            val username = args.username

            val homeViewModel = hiltViewModel<HomeViewModel>()
            val uiState by homeViewModel.state.collectAsStateWithLifecycle()
            HomeScreen(username = username, uiState = uiState, onEvent = homeViewModel::onEvent)
        }

    }
}