package com.senkou.tv.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.senkou.data.BackgroundRepository
import com.senkou.data.MoviesRepository
import com.senkou.framework.remote.arte7.WebMovieDatasource
import com.senkou.framework.remote.tmdb.TmdbClient
import com.senkou.framework.remote.tmdb.TmdbServerDataSource
import com.senkou.tv.ui.detail.DetallePelicula
import com.senkou.tv.ui.detail.DetalleViewModel
import com.senkou.tv.ui.mainscreen.MainScreen
import com.senkou.tv.ui.mainscreen.PeliListViewModel
import com.senkou.tv.ui.splash.SplashScreen
import com.senkou.usecases.CargarBackgroundUseCase
import com.senkou.usecases.CargarCarteleraUseCase
import com.senkou.usecases.CargarDetalleUseCase

@Composable
fun AppNavitagion() {
   val navController = rememberNavController()

   val moviesRepository = MoviesRepository(WebMovieDatasource())
   val backgroundRepository =
      BackgroundRepository(TmdbServerDataSource(TmdbClient("https://api.themoviedb.org/3/").instance))

   val model = PeliListViewModel(
      CargarCarteleraUseCase(moviesRepository),
      CargarBackgroundUseCase(backgroundRepository),
   )

   NavHost(
      navController = navController,
      startDestination = SplashScreen
   ) {

      composable<SplashScreen> {
         SplashScreen(model) {
            navController.navigate(MainScreen)
         }
      }

      composable<MainScreen> {
         MainScreen(model) { idEspectaculo ->
            navController.navigate(DetalleScreen(idEspectaculo))
         }
      }

      composable<DetalleScreen> { backstackEntry ->
         val idEspectaculo = backstackEntry.toRoute<DetalleScreen>().idEspectaculo

         DetallePelicula(
            DetalleViewModel(
               idEspectaculo = idEspectaculo,
               cargarDetalle = CargarDetalleUseCase(moviesRepository),
               cargarBackground = CargarBackgroundUseCase(backgroundRepository),
            )
         )
      }
   }
}