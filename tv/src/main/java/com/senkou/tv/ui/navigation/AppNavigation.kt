package com.senkou.tv.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.senkou.tv.ui.detail.DetailScreen
import com.senkou.tv.ui.mainscreen.MainScreen
import com.senkou.tv.ui.splash.SplashScreen

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
         DetailScreen()
      }
   }
}