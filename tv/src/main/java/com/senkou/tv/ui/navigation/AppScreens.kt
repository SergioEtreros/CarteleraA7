package com.senkou.tv.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object Splash

@Serializable
object Main

@Serializable
data class Detail(
   val movieId: Int,
)
