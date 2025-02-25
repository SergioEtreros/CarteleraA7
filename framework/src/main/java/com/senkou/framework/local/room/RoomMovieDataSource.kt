package com.senkou.framework.local.room

import com.senkou.data.LocalDataSource
import com.senkou.domain.model.Pelicula
import com.senkou.domain.model.Sesion
import com.senkou.framework.local.room.dao.PeliculaDao
import com.senkou.framework.local.room.dao.ProximoEstrenoDao
import com.senkou.framework.local.room.dao.SesionDao
import com.senkou.framework.toDomain
import com.senkou.framework.toPeliculaEntity
import com.senkou.framework.toProximoEstrenoEntity
import com.senkou.framework.toSesionEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomMovieDataSource(
   private val peliculaDao: PeliculaDao,
   private val proximoEstrenoDao: ProximoEstrenoDao,
   private val sesionDao: SesionDao
) : LocalDataSource {

   override val peliculas: Flow<List<Pelicula>>
      get() = peliculaDao.getPeliculas().map {
         it.map { peliculaEntity -> peliculaEntity.toDomain() }
      }

   override val proximosEstrenos: Flow<List<Pelicula>>
      get() = proximoEstrenoDao.getEstrenos().map {
         it.map { proximoEstrenoEntity -> proximoEstrenoEntity.toDomain() }
      }


   override fun getSesiones(idEspectaculo: Int): Flow<List<Sesion>> =
      sesionDao.getSesiones(idEspectaculo).map { 
         it.map { sesionConFondo -> sesionConFondo.toDomain() }
      }

   override suspend fun savePeliculas(peliculas: List<Pelicula>) {
      peliculaDao.insertPeliculas(peliculas.map { it.toPeliculaEntity() })
   }

   override suspend fun saveProximosEstrenos(proximosEstrenos: List<Pelicula>) {
      proximoEstrenoDao.insertEstrenos(proximosEstrenos.map { it.toProximoEstrenoEntity() })
   }

   override suspend fun saveSesiones(sesiones: List<Sesion>) {
      sesionDao.insertSesiones(sesiones.map { it.toSesionEntity() })
   }

   override suspend fun deletePeliculas() {
      peliculaDao.deletePeliculas()
   }

   override suspend fun deleteEstrenos() {
      proximoEstrenoDao.deleteEstrenos()
   }
}

