package com.senkou.wear.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.senkou.wear.ui.screens.detailscreen.DetailScreen
import com.senkou.wear.ui.screens.mainscreen.MainScreen
import com.senkou.wear.ui.screens.splashscreen.SplashScreen

@Composable
fun AppNavitagion() {

   val navController = rememberSwipeDismissableNavController()

   SwipeDismissableNavHost(
      navController = navController,
      startDestination = AppScreens.Splash.route
   ) {
      composable(AppScreens.Splash.route) {
         SplashScreen {
            navController.navigate(AppScreens.Main.route) {
               popUpTo(0) { inclusive = true }
            }
         }
      }
      composable(AppScreens.Main.route) {
         MainScreen { movieId ->
            navController.navigate("${AppScreens.Detail.route}/$movieId")
         }
      }
      composable(
         route = "${AppScreens.Detail.route}/{movieId}",
         arguments = listOf(navArgument("movieId") { type = NavType.IntType })
      ) { navBackStackEntry ->
         val movieId = navBackStackEntry.arguments?.getInt("movieId", -1)
         movieId?.let {
            DetailScreen()
         }
      }
   }
}