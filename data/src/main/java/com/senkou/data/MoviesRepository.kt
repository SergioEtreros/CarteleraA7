package com.senkou.data

import com.senkou.domain.model.Pelicula
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MoviesRepository @Inject constructor(
   private val localDataSource: LocalDataSource,
   private val webMovieDatasource: RemoteDataSource,
   private val backgroundDataSource: BackgroundDataSource
) {

   val peliculas
      get() = localDataSource.movies.onEach { peliculas ->
         if (peliculas.isEmpty()) {
            val theatreMovies = webMovieDatasource.getCartelera()
            localDataSource.saveMovies(
               theatreMovies.peliculas.addmoviesBackground(
                  backgroundDataSource
               )
            )
            localDataSource.saveUpcomingMovies(
               theatreMovies.proximosEstrenos.addmoviesBackground(
                  backgroundDataSource
               )
            )
            localDataSource.saveSessions(theatreMovies.sesiones)
         }
      }

   val proximosEstrenos
      get() = localDataSource.upcomingMovies


   fun getSessions(movieId: Int) = localDataSource.getSessions(movieId)

   suspend fun deleteMovies() = localDataSource.deleteMovies()
   suspend fun deleteUpcomingMovies() = localDataSource.deleteUpcomingMovies()
}

private suspend fun List<Pelicula>.addmoviesBackground(backgroundDataSource: BackgroundDataSource) =
   map {
      it.copy(
         background = backgroundDataSource.getMovieBackgroundByName(
            it.tituloOriginal,
            it.fechaEstreno.substringBefore("-")
         ) ?: it.cartel
      )
   }

