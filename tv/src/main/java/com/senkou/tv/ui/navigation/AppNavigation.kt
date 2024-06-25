package com.senkou.tv.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.senkou.data.MoviesRepository
import com.senkou.data.VideoRepository
import com.senkou.framework.remote.WebMovieDatasource
import com.senkou.tv.ui.detail.DetallePelicula
import com.senkou.tv.ui.detail.DetalleViewModel
import com.senkou.tv.ui.mainscreen.MainScreen
import com.senkou.tv.ui.mainscreen.PeliListViewModel
import com.senkou.tv.ui.splash.SplashScreen
import com.senkou.usecases.CargarCarteleraUseCase
import com.senkou.usecases.CargarDetalleUseCase
import com.senkou.usecases.ReproducirTrailerUseCase

@Composable
fun AppNavitagion() {
   val navController = rememberNavController()

   val moviesRepository = MoviesRepository(WebMovieDatasource())
   val model = PeliListViewModel(CargarCarteleraUseCase(moviesRepository))

   val videoRepository = VideoRepository(
      com.senkou.framework.YoutubeDatasource(
         context = LocalContext.current
      )
   )
   NavHost(
      navController = navController,
      startDestination = AppScreens.SplashScreen.route
   ) {
      composable(AppScreens.SplashScreen.route) {
         SplashScreen(model) {
            navController.navigate(AppScreens.MainScreen.route)
         }
      }
      composable(AppScreens.MainScreen.route) {
         MainScreen(model) { idEspectaculo ->
            navController.navigate("${AppScreens.DetalleScreen.route}/$idEspectaculo")
         }
      }
      composable(
         route = "${AppScreens.DetalleScreen.route}/{iDEspectaculo}",
         arguments = listOf(navArgument("iDEspectaculo") { type = NavType.IntType })
      ) { navBackStackEntry ->
         val idEspectaculo = navBackStackEntry.arguments?.getInt("iDEspectaculo", -1)
         idEspectaculo?.let {
            DetallePelicula(
               DetalleViewModel(
                  idEspectaculo,
                  cargarDetalle = CargarDetalleUseCase(moviesRepository),
                  reproducirTrailer = ReproducirTrailerUseCase(videoRepository)
               )
            )
         }
      }
   }
}