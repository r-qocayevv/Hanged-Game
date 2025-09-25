package com.revan.hanged

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.revan.hanged.navigation.NavGraph
import com.revan.hanged.navigation.Navigator
import com.revan.hanged.presentation.home.HomeScreen
import com.revan.hanged.presentation.home.HomeViewModel
import com.revan.hanged.presentation.login.LoginScreen
import com.revan.hanged.presentation.login.LoginState
import com.revan.hanged.presentation.login.LoginViewModel
import com.revan.hanged.presentation.register.RegisterScreen
import com.revan.hanged.presentation.register.RegisterViewModel
import com.revan.hanged.ui.theme.HangedTheme
import dagger.hilt.android.AndroidEntryPoint
import jakarta.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var navigator : Navigator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController  = rememberNavController()
            val localLifecycle = LocalLifecycleOwner.current.lifecycle

            HangedTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { it

                    NavGraph(navController = navController)
                    LaunchedEffect(navigator) {
                        navigator.handleNavigation(navController,localLifecycle)
                    }
                }
            }
        }
    }
}


