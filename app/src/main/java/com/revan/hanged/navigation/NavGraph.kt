package com.revan.hanged.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.revan.hanged.domain.model.CustomNavTypeRoomInfo
import com.revan.hanged.domain.model.RoomInfo
import com.revan.hanged.presentation.game.GameScreen
import com.revan.hanged.presentation.game.GameViewModel
import com.revan.hanged.presentation.home.HomeScreen
import com.revan.hanged.presentation.home.HomeViewModel
import com.revan.hanged.presentation.login.LoginScreen
import com.revan.hanged.presentation.login.LoginViewModel
import com.revan.hanged.presentation.register.RegisterScreen
import com.revan.hanged.presentation.register.RegisterViewModel
import com.revan.hanged.presentation.splash.SplashScreen
import com.revan.hanged.presentation.splash.SplashViewModel
import kotlin.reflect.typeOf

@Composable
fun NavGraph(navController: NavHostController) {


    NavHost(
        navController = navController,
        startDestination = ScreenRoute.SplashScreen
    ) {

        composable <ScreenRoute.SplashScreen> {
            val splashViewModel = hiltViewModel<SplashViewModel>()
            SplashScreen()
        }

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

        composable<ScreenRoute.Game> (
            typeMap = mapOf(typeOf<RoomInfo>() to CustomNavTypeRoomInfo)
        ) {
            val args = it.toRoute<ScreenRoute.Game>()
            val roomInfo = args.roomInfo
            val gameViewModel = hiltViewModel<GameViewModel>()
            val uiState by gameViewModel.state.collectAsStateWithLifecycle()

            GameScreen(roomInfo = roomInfo,uiState = uiState, onEvent = gameViewModel::onEvent)
        }

    }
}