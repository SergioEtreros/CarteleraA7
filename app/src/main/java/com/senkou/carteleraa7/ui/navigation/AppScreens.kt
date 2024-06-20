package com.senkou.carteleraa7.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object SplashScreen

@Serializable
object MainScreen

@Serializable
data class DetalleScreen(val idEspectaculo: Int)
