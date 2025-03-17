package com.senkou.framework.local.room

import com.senkou.data.LocalDataSource
import com.senkou.domain.model.Pelicula
import com.senkou.domain.model.Sesion
import com.senkou.framework.local.room.dao.MovieDao
import com.senkou.framework.local.room.dao.SessionDao
import com.senkou.framework.local.room.dao.UpcomingMoviesDao
import com.senkou.framework.toDomain
import com.senkou.framework.toMovieEntity
import com.senkou.framework.toSessionEntity
import com.senkou.framework.toUpcomingMoviesEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomMovieDataSource @Inject constructor(
   private val movieDao: MovieDao,
   private val upcomingMoviesDao: UpcomingMoviesDao,
   private val sessionDao: SessionDao
) : LocalDataSource {

   override val movies: Flow<List<Pelicula>>
      get() = movieDao.getMovies().map {
         it.map { movieEntity -> movieEntity.toDomain() }
      }

   override val upcomingMovies: Flow<List<Pelicula>>
      get() = upcomingMoviesDao.getUpcomingMovies().map {
         it.map { upcomingMoviesEntity -> upcomingMoviesEntity.toDomain() }
      }


   override fun getSessions(movieId: Int): Flow<List<Sesion>> =
      sessionDao.getSessions(movieId).map {
         it.map { sessionWithBackground -> sessionWithBackground.toDomain() }
      }

   override suspend fun saveMovies(movies: List<Pelicula>) {
      movieDao.insertMovies(movies.map { it.toMovieEntity() })
   }

   override suspend fun saveUpcomingMovies(upcomingMovies: List<Pelicula>) {
      upcomingMoviesDao.insertUpcomingMovies(upcomingMovies.map { it.toUpcomingMoviesEntity() })
   }

   override suspend fun saveSessions(sessions: List<Sesion>) {
      sessionDao.insertSessions(sessions.map { it.toSessionEntity() })
   }

   override suspend fun deleteMovies() {
      movieDao.deleteAllMovies()
   }

   override suspend fun deleteUpcomingMovies() {
      upcomingMoviesDao.deleteAllUpcomingMovies()
   }
}

