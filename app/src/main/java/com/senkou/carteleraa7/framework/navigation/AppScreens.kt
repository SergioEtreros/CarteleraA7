package com.senkou.carteleraa7.framework.navigation

sealed class AppScreens(val route: String) {
    data object SplashScreen : AppScreens("splash_screen")
    data object MainScreen : AppScreens("main_screen")
    data object DetalleScreen : AppScreens("detalle_screen")
}