package com.senkou.tv.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import androidx.room.Room
import com.senkou.data.MoviesRepository
import com.senkou.framework.local.room.RoomMovieDataSource
import com.senkou.framework.local.room.db.CarteleraDB
import com.senkou.framework.remote.arte7.WebMovieDatasource
import com.senkou.framework.remote.tmdb.TmdbClient
import com.senkou.framework.remote.tmdb.TmdbServerDataSource
import com.senkou.tv.ui.detail.DetallePelicula
import com.senkou.tv.ui.detail.DetalleViewModel
import com.senkou.tv.ui.mainscreen.MainScreen
import com.senkou.tv.ui.mainscreen.PeliListViewModel
import com.senkou.tv.ui.splash.SplashScreen
import com.senkou.usecases.CargarDetalleUseCase
import com.senkou.usecases.CargarPeliculasUseCase
import com.senkou.usecases.CargarProximosEstrenosUseCase

@Composable
fun AppNavitagion() {
   val navController = rememberNavController()

   val db = Room.databaseBuilder(
      LocalContext.current.applicationContext,
      CarteleraDB::class.java,
      "Cartelera"
   ).build()

   LaunchedEffect(true) {
      db.peliculaDao().deletePeliculas()
      db.proximoEstrenoDao().deleteEstrenos()
   }

   val backgroundDataSource = TmdbServerDataSource(TmdbClient("https://api.themoviedb.org/3/").instance)

   val moviesRepository = MoviesRepository(
      localDataSource = RoomMovieDataSource(
         peliculaDao = db.peliculaDao(),
         proximoEstrenoDao = db.proximoEstrenoDao(),
         sesionDao = db.sesionDao()
      ),
      webMovieDatasource = WebMovieDatasource(),
      backgroundDataSource = backgroundDataSource

   )

   val model = PeliListViewModel(
      cargarPeliculasUseCase = CargarPeliculasUseCase(moviesRepository),
      cargarProximosEstrenosUseCase = CargarProximosEstrenosUseCase(moviesRepository)
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
            )
         )
      }
   }
}