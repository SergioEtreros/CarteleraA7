package com.senkou.carteleraa7.framework.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.senkou.carteleraa7.framework.ui.detail.DetallePelicula
import com.senkou.carteleraa7.framework.ui.detail.DetalleViewModel
import com.senkou.carteleraa7.framework.ui.mainscreen.MainScreen
import com.senkou.carteleraa7.framework.ui.mainscreen.PeliViewModel
import com.senkou.carteleraa7.framework.ui.splash.SplashScreen

@Composable
fun AppNavitagion(model: PeliViewModel = hiltViewModel()) {
   val navController = rememberNavController()
   NavHost(
      navController = navController,
      startDestination = AppScreens.SplashScreen.route
   ) {
      composable(AppScreens.SplashScreen.route) {
         SplashScreen(navController, model)
      }

      composable(AppScreens.MainScreen.route) {
         MainScreen(navController, model)
      }

      composable(
         route = "${AppScreens.DetalleScreen.route}/{itemId}",
         arguments = listOf(navArgument("itemId") { type = NavType.IntType })
      ) { navBackStackEntry ->
         val id = navBackStackEntry.arguments?.getInt("itemId")
         id?.let {
            val detalleModel = DetalleViewModel(model.getSesiones(it))
            DetallePelicula(
               navController,
               detalleModel
            )
         }
      }
   }
}