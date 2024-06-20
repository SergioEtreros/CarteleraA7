package com.senkou.wear.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.senkou.wear.ui.screens.detailscreen.DetallePelicula
import com.senkou.wear.ui.screens.mainscreen.MainScreen
import com.senkou.wear.ui.screens.mainscreen.PeliViewModel
import com.senkou.wear.ui.screens.splashscreen.SplashScreen

@Composable
fun AppNavitagion(model: PeliViewModel) {
   val navController = rememberSwipeDismissableNavController()
   SwipeDismissableNavHost(
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
         val id = navBackStackEntry.arguments?.getInt("itemId", -1)
         id?.let {
            model.getPeliculas().getOrNull(id)?.let {
               DetallePelicula(
                  model.getSesiones(it.iDEspectaculo),
               )
            }
         }
      }
   }
}