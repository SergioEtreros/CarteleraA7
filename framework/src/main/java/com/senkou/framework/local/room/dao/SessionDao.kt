package com.senkou.framework.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.senkou.framework.local.room.SessionWithBackground
import com.senkou.framework.local.room.entities.SessionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SessionDao {

   @Transaction
   @Query("SELECT * FROM sessiones WHERE movieId = :movieId")
   fun getSessions(movieId: Int): Flow<List<SessionWithBackground>>

   @Insert (onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertSessions(sessions: List<SessionEntity>)
}