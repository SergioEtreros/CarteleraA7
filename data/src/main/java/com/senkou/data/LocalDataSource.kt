package com.senkou.data

import com.senkou.domain.model.Pelicula
import com.senkou.domain.model.Sesion
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

   val movies: Flow<List<Pelicula>>
   val upcomingMovies: Flow<List<Pelicula>>
   fun getSessions(movieId: Int): Flow<List<Sesion>>

   suspend fun saveMovies(movies: List<Pelicula>)
   suspend fun saveUpcomingMovies(upcomingMovies: List<Pelicula>)

   suspend fun saveSessions(sessions: List<Sesion>)

   suspend fun deleteMovies()

   suspend fun deleteUpcomingMovies()
}