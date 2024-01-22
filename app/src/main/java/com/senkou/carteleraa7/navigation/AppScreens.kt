package com.senkou.carteleraa7.navigation

sealed class AppScreens(val route: String) {
    data object SplashScreen : AppScreens("splash_screen")
    data object MainScreen : AppScreens("main_screen")
    data object DetalleScreen : AppScreens("detalle_screen")
    data object DetalleProximoEstreno : AppScreens("detalle_proximo_estreno")
}