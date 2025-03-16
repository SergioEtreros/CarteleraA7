package com.senkou.wear.ui.navigation

sealed class AppScreens(val route: String) {
   data object Splash : AppScreens("splash_screen")
   data object Main : AppScreens("main_screen")
   data object Detail : AppScreens("detail_screen")
}