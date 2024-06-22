package com.senkou.wear.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.senkou.data.MoviesRepository
import com.senkou.data.VideoRepository
import com.senkou.framework.remote.WebMovieDatasource
import com.senkou.usecases.CargarCarteleraUseCase
import com.senkou.usecases.CargarDetalleUseCase
import com.senkou.usecases.ReproducirTrailerUseCase
import com.senkou.wear.ui.screens.detailscreen.DetallePelicula
import com.senkou.wear.ui.screens.detailscreen.DetalleViewModel
import com.senkou.wear.ui.screens.mainscreen.MainScreen
import com.senkou.wear.ui.screens.mainscreen.PeliViewModel
import com.senkou.wear.ui.screens.splashscreen.SplashScreen

@Composable
fun AppNavitagion() {
   val navController = rememberSwipeDismissableNavController()

   val moviesRepository = MoviesRepository(WebMovieDatasource())
   val model = PeliViewModel(CargarCarteleraUseCase(moviesRepository))

   val videoRepository = VideoRepository(
      com.senkou.framework.YoutubeDatasource(
         context = LocalContext.current
      )
   )

   SwipeDismissableNavHost(
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
            ) {
               navController.popBackStack()
            }
         }
      }
   }
}