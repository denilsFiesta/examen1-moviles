package com.ucb.testmovuno.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ucb.testmovuno.home.HomeUI
import com.ucb.testmovuno.myfavoritebooks.MyFavoriteBooksUI
import com.ucb.testmovuno.searchbooks.SearchBooksUI

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.Home.route
    ) {
        composable(Screens.Home.route) {
            HomeUI(
                onGoToSearch = { navController.navigate(Screens.Search.route) },
                onGoToFavorites = { navController.navigate(Screens.Favorites.route) }
            )
        }

        composable(Screens.Search.route) {
            SearchBooksUI(
                onBack = { navController.popBackStack() }
            )
        }

        composable(Screens.Favorites.route) {
            MyFavoriteBooksUI(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
