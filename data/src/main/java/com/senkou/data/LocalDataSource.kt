package com.senkou.data

import com.senkou.domain.model.Pelicula
import com.senkou.domain.model.Sesion
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

   val peliculas: Flow<List<Pelicula>>
   val proximosEstrenos: Flow<List<Pelicula>>
   fun getSesiones(idEspectaculo: Int): Flow<List<Sesion>>

   suspend fun savePeliculas(peliculas: List<Pelicula>)
   suspend fun saveProximosEstrenos(proximosEstrenos: List<Pelicula>)

   suspend fun saveSesiones(sesiones: List<Sesion>)

   suspend fun deletePeliculas()

   suspend fun deleteEstrenos()
}