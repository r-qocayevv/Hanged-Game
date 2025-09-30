package com.revan.hanged.navigation

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.NavHostController
import com.revan.hanged.utils.safeNavigate
import com.revan.hanged.utils.safePopBackStack
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest

class Navigator {

    val navigation = MutableSharedFlow<NavigationCommand>()

    suspend fun sendNavigation (navigationCommand: NavigationCommand) {
        navigation.emit(navigationCommand)
    }

    suspend fun handleNavigation(navController: NavHostController, lifecycle: Lifecycle) {
        navigation
            .asSharedFlow()
            .flowWithLifecycle(lifecycle)
            .collectLatest {
                when (it) {
                    is NavigationCommand.OnNavigate -> {
                        navController.safeNavigate(route = it.route, popUpTo = it.popUpTo)
                    }

                    NavigationCommand.ToBack -> {
                        navController.safePopBackStack()
                    }
                }
            }
    }

}