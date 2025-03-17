package com.senkou.framework.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.senkou.framework.local.room.entities.UpcomingMoviesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UpcomingMoviesDao {

   @Query("SELECT * FROM upcoming")
   fun getUpcomingMovies(): Flow<List<UpcomingMoviesEntity>>

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertUpcomingMovies(upcomingMovies: List<UpcomingMoviesEntity>)

   @Query("DELETE FROM upcoming")
   suspend fun deleteAllUpcomingMovies()
}