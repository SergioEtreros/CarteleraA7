package com.senkou.framework.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.senkou.framework.local.room.SesionConFondo
import com.senkou.framework.local.room.entities.SesionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SesionDao {

   @Query("SELECT * FROM sesiones WHERE idEspectaculo = :idEspectaculo")
   fun getSesiones(idEspectaculo: Int): Flow<List<SesionConFondo>>

   @Insert (onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertSesiones(sesiones: List<SesionEntity>)
}