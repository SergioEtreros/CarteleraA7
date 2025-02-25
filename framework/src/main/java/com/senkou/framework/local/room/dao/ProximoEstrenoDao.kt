package com.senkou.framework.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.senkou.framework.local.room.entities.ProximoEstrenoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProximoEstrenoDao {

   @Query("SELECT * FROM estrenos")
   fun getEstrenos(): Flow<List<ProximoEstrenoEntity>>

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertEstrenos(proximoEstrenos: List<ProximoEstrenoEntity>)

   @Query("DELETE FROM estrenos")
   suspend fun deleteEstrenos()
}