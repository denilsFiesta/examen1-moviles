package com.ucb.testmovuno.navigation

sealed class Screens(
    val route: String
) {
    object Home : Screens("home_screen")
    object Search : Screens("search_books_screen")
    object Favorites : Screens("favorite_books_screen")
}
