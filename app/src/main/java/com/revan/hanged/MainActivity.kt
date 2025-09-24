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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.revan.hanged.presentation.home.HomeScreen
import com.revan.hanged.presentation.home.HomeViewModel
import com.revan.hanged.presentation.login.LoginScreen
import com.revan.hanged.presentation.login.LoginState
import com.revan.hanged.ui.theme.HangedTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HangedTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { it

                    val homeViewModel = hiltViewModel<HomeViewModel>()
                    val uiState by homeViewModel.state.collectAsStateWithLifecycle()
                    HomeScreen(uiState = uiState, onEvent = homeViewModel::onEvent)
                }
            }
        }
    }
}


