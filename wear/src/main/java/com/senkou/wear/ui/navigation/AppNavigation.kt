package com.senkou.wear.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.room.Room
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.senkou.data.MoviesRepository
import com.senkou.framework.local.room.RoomMovieDataSource
import com.senkou.framework.local.room.db.CarteleraDB
import com.senkou.framework.remote.arte7.WebMovieDatasource
import com.senkou.framework.remote.tmdb.TmdbClient
import com.senkou.framework.remote.tmdb.TmdbServerDataSource
import com.senkou.usecases.CargarDetalleUseCase
import com.senkou.usecases.CargarPeliculasUseCase
import com.senkou.wear.ui.screens.detailscreen.DetallePelicula
import com.senkou.wear.ui.screens.detailscreen.DetalleViewModel
import com.senkou.wear.ui.screens.mainscreen.MainScreen
import com.senkou.wear.ui.screens.mainscreen.PeliViewModel
import com.senkou.wear.ui.screens.splashscreen.SplashScreen

@Composable
fun AppNavitagion() {
   val navController = rememberSwipeDismissableNavController()

   val db = Room.databaseBuilder(
      LocalContext.current.applicationContext,
      CarteleraDB::class.java,
      "Cartelera"
   ).build()

   val backgroundDataSource =
      TmdbServerDataSource(TmdbClient("https://api.themoviedb.org/3/").instance)

   val moviesRepository = MoviesRepository(
      localDataSource = RoomMovieDataSource(
         peliculaDao = db.peliculaDao(),
         proximoEstrenoDao = db.proximoEstrenoDao(),
         sesionDao = db.sesionDao()
      ),
      webMovieDatasource = WebMovieDatasource(),
      backgroundDataSource = backgroundDataSource
   )

   val model = PeliViewModel(
      cargarPeliculasUseCase = CargarPeliculasUseCase(moviesRepository),
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
               )
            )
         }
      }
   }
}