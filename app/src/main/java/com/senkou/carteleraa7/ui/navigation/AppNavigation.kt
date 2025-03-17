package com.senkou.carteleraa7.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.senkou.carteleraa7.ui.detail.DetailScreen
import com.senkou.carteleraa7.ui.mainscreen.MainScreen
import com.senkou.carteleraa7.ui.splash.SplashScreen

@Composable
fun AppNavitagion() {

   val navController = rememberNavController()

   NavHost(
      navController = navController,
      startDestination = Splash
   ) {
      composable<Splash> {
         SplashScreen {
            navController.navigate(Main) {
               popUpTo(0) { inclusive = true }
            }
         }
      }

      composable<Main> {
         MainScreen { movieId ->
            navController.navigate(Detail(movieId))
         }
      }

      composable<Detail> {
         DetailScreen { navController.popBackStack() }
      }
   }
}