package com.senkou.framework.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.senkou.framework.local.room.entities.PeliculaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PeliculaDao {

   @Query("SELECT * FROM peliculas")
   fun getPeliculas(): Flow<List<PeliculaEntity>>

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertPeliculas(peliculas: List<PeliculaEntity>)

   @Query("DELETE FROM peliculas")
   suspend fun deletePeliculas()
}

