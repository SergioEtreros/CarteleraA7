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
import com.senkou.usecases.LoadDetailUseCase
import com.senkou.usecases.LoadMoviesUseCase
import com.senkou.wear.ui.screens.detailscreen.DetailScreen
import com.senkou.wear.ui.screens.detailscreen.DetailViewModel
import com.senkou.wear.ui.screens.mainscreen.MainScreen
import com.senkou.wear.ui.screens.mainscreen.MoviesListViewModel
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

   val model = MoviesListViewModel(
      loadMoviesUseCase = LoadMoviesUseCase(moviesRepository),
   )

   SwipeDismissableNavHost(
      navController = navController,
      startDestination = AppScreens.Splash.route
   ) {
      composable(AppScreens.Splash.route) {
         SplashScreen(model) {
            navController.navigate(AppScreens.Main.route) {
               popUpTo(0) { inclusive = true }
            }
         }
      }
      composable(AppScreens.Main.route) {
         MainScreen(model) { movieId ->
            navController.navigate("${AppScreens.Detail.route}/$movieId")
         }
      }
      composable(
         route = "${AppScreens.Detail.route}/{movieId}",
         arguments = listOf(navArgument("movieId") { type = NavType.IntType })
      ) { navBackStackEntry ->
         val movieId = navBackStackEntry.arguments?.getInt("movieId", -1)
         movieId?.let {
            DetailScreen(
               DetailViewModel(
                  movieId = movieId,
                  loadDetailUseCase = LoadDetailUseCase(moviesRepository),
               )
            )
         }
      }
   }
}