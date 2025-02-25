package com.senkou.carteleraa7.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import androidx.room.Room
import com.senkou.carteleraa7.ui.detail.DetallePelicula
import com.senkou.carteleraa7.ui.detail.DetalleViewModel
import com.senkou.carteleraa7.ui.mainscreen.MainScreen
import com.senkou.carteleraa7.ui.mainscreen.PeliListViewModel
import com.senkou.carteleraa7.ui.splash.SplashScreen
import com.senkou.data.MoviesRepository
import com.senkou.data.VideoRepository
import com.senkou.framework.local.room.RoomMovieDataSource
import com.senkou.framework.local.room.db.CarteleraDB
import com.senkou.framework.remote.arte7.WebMovieDatasource
import com.senkou.framework.remote.tmdb.TmdbClient
import com.senkou.framework.remote.tmdb.TmdbServerDataSource
import com.senkou.framework.remote.youtube.YoutubeDatasource
import com.senkou.usecases.CargarDetalleUseCase
import com.senkou.usecases.CargarPeliculasUseCase
import com.senkou.usecases.CargarProximosEstrenosUseCase
import com.senkou.usecases.ReproducirTrailerUseCase

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

   val peliListViewModel = PeliListViewModel(
      cargarPeliculasUseCase = CargarPeliculasUseCase(moviesRepository),
      cargarProximosEstrenosUseCase = CargarProximosEstrenosUseCase(moviesRepository)
   )

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