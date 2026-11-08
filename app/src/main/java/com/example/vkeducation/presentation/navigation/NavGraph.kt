package com.example.vkeducation.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vkeducation.presentation.screen.appdetails.AppDetailsRoute
import com.example.vkeducation.presentation.screen.applist.AppListRoute

@Composable
fun NavGraph(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier.fillMaxSize(),
        navController = navController,
        startDestination = NavAppList
    ) {
        composable<NavAppList> {
            AppListRoute(
                onAppClick = { navController.navigate(NavAppDetails) }
            )
        }
        composable<NavAppDetails> {
            AppDetailsRoute(
                onBackClick = {
                    navController.navigate(NavAppList) {
                        // Убираем карточку из стека и не создаём второй экземпляр списка
                        popUpTo<NavAppList>()
                        launchSingleTop = true
                    }
                },
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .safeDrawingPadding()
            )
        }
    }
}
