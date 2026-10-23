package com.example.vkeducation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vkeducation.AppListScreen
import com.example.vkeducation.appdetails.AppDetailsScreen
import com.example.vkeducation.appsDetails

@Composable
fun NavGraph(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier.fillMaxSize(),
        navController = navController,
        startDestination = AppList
    ) {
        composable<AppList> {
            AppListScreen(
                appDetails = appsDetails,
                onAppClick = { navController.navigate(AppDetails) }
            )
        }
        composable<AppDetails> {
            AppDetailsScreen(
                onBackClick = { navController.navigateUp() },
                onShowcaseAppClick = {
                    navController.navigate(AppList) {
                        // Убираем карточку из стека и не создаём второй экземпляр списка
                        popUpTo<AppList>()
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
