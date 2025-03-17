package com.senkou.framework.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.senkou.framework.local.room.entities.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

   @Query("SELECT * FROM movies")
   fun getMovies(): Flow<List<MovieEntity>>

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertMovies(movies: List<MovieEntity>)

   @Query("DELETE FROM movies")
   suspend fun deleteAllMovies()
}

