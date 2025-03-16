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
import com.senkou.tv.ui.detail.DetailScreen
import com.senkou.tv.ui.detail.DetailViewModel
import com.senkou.tv.ui.mainscreen.MainScreen
import com.senkou.tv.ui.mainscreen.MoviesListViewModel
import com.senkou.tv.ui.splash.SplashScreen
import com.senkou.usecases.LoadDetailUseCase
import com.senkou.usecases.LoadMoviesUseCase
import com.senkou.usecases.LoadUpcomingMoviesUseCase

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

   val model = MoviesListViewModel(
      loadMoviesUseCase = LoadMoviesUseCase(moviesRepository),
      loadUpcomingMoviesUseCase = LoadUpcomingMoviesUseCase(moviesRepository)
   )

   NavHost(
      navController = navController,
      startDestination = Splash
   ) {

      composable<Splash> {
         SplashScreen(model) {
            navController.navigate(Main) {
               popUpTo(0) { inclusive = true }
            }
         }
      }

      composable<Main> {
         MainScreen(model) { movieId ->
            navController.navigate(Detail(movieId))
         }
      }

      composable<Detail> { backstackEntry ->
         val movieId = backstackEntry.toRoute<Detail>().movieId

         DetailScreen(
            DetailViewModel(
               movieId = movieId,
               loadDetailUseCase = LoadDetailUseCase(moviesRepository),
            )
         )
      }
   }
}