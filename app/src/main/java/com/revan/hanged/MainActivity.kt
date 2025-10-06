package com.revan.hanged

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.compose.rememberNavController
import com.revan.hanged.navigation.NavGraph
import com.revan.hanged.navigation.Navigator
import com.revan.hanged.ui.theme.HangedTheme
import com.revan.hanged.utils.Toaster
import dagger.hilt.android.AndroidEntryPoint
import jakarta.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var navigator : Navigator
    @Inject
    lateinit var toaster: Toaster
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController  = rememberNavController()
            val localLifecycle = LocalLifecycleOwner.current.lifecycle
            val context = LocalContext.current

            HangedTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { it

                    NavGraph(navController = navController)
                    LaunchedEffect(navigator) {
                        navigator.handleNavigation(navController,localLifecycle)
                    }
                    LaunchedEffect(toaster) {
                        toaster.handleToastMessage(context = context, lifecycle = localLifecycle)
                    }
                }
            }
        }
    }
}


