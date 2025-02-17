package com.senkou.data

class MoviesRepository(
   private val webMovieDatasource: RemoteDataSource
) {
   suspend fun getCartelera() = webMovieDatasource.getCartelera()
   suspend fun getSesiones(idEspectaculo: Int) = webMovieDatasource.getSesiones(idEspectaculo)
}