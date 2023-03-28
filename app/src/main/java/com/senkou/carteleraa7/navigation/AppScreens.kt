package com.senkou.carteleraa7.navigation

sealed class AppScreens(val route: String) {
    object SplashScreen: AppScreens("splash_screen")
    object MainScreen: AppScreens("main_screen")
    object DetalleScreen: AppScreens("detalle_screen")
}