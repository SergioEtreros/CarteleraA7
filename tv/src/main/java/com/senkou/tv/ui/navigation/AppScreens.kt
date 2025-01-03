package com.senkou.tv.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object SplashScreen

@Serializable
object MainScreen

@Serializable
data class DetalleScreen(
   val idEspectaculo: Int,
//   val background: String
)
