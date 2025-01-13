package com.senkou.carteleraa7.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.senkou.carteleraa7.ui.detail.DetallePelicula
import com.senkou.carteleraa7.ui.detail.DetalleViewModel
import com.senkou.carteleraa7.ui.mainscreen.MainScreen
import com.senkou.carteleraa7.ui.mainscreen.PeliListViewModel
import com.senkou.carteleraa7.ui.splash.SplashScreen
import com.senkou.data.MoviesRepository
import com.senkou.data.VideoRepository
import com.senkou.framework.remote.arte7.WebMovieDatasource
import com.senkou.framework.remote.youtube.YoutubeDatasource
import com.senkou.usecases.CargarCarteleraUseCase
import com.senkou.usecases.CargarDetalleUseCase
import com.senkou.usecases.ReproducirTrailerUseCase

@Composable
fun AppNavitagion() {
   val navController = rememberNavController()

   val moviesRepository = MoviesRepository(WebMovieDatasource())
   val peliListViewModel = PeliListViewModel(CargarCarteleraUseCase(moviesRepository))

   val videoRepository = VideoRepository(YoutubeDatasource(context = LocalContext.current))

   NavHost(
      navController = navController,
      startDestination = SplashScreen
   ) {
      composable<SplashScreen> {
         SplashScreen(peliListViewModel) {
            navController.navigate(MainScreen)
         }
      }

      composable<MainScreen> {
         MainScreen(peliListViewModel) { pelicula ->
            navController.navigate(DetalleScreen(pelicula))
         }
      }

      composable<DetalleScreen> { backstackEntry ->
         val idEspectaculo = backstackEntry.toRoute<DetalleScreen>().idEspectaculo

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