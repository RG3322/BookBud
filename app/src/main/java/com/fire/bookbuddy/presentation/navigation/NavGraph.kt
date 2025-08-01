package com.fire.bookbuddy.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.fire.bookbuddy.presentation.AllBooksByCategory.BooksByCategory
import com.fire.bookbuddy.presentation.HomeScreen.HomeScreen
import com.fire.bookbuddy.presentation.PdfViewerScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.HomeScreen
    )
    {
        composable<Routes.HomeScreen> {
            HomeScreen(navHostController = navController)
        }
        composable<Routes.ShowPdfScreen> { backStackEntry ->
            val data = backStackEntry.toRoute<Routes.ShowPdfScreen>()
            PdfViewerScreen(url = data.url)
        }
        composable<Routes.BooksByCategory> {backStackEntry ->
            val data = backStackEntry.toRoute<Routes.BooksByCategory>()
            Routes.BooksByCategory(category = data.category,navController=navController)
        }
    }
}

/**
 * main error file
 */